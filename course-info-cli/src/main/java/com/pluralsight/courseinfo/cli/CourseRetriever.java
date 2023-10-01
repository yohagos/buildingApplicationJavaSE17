package com.pluralsight.courseinfo.cli;

import com.pluralsight.courseinfo.cli.service.CourseRetrievelService;
import com.pluralsight.courseinfo.cli.service.CourseStorageService;
import com.pluralsight.courseinfo.cli.service.PluralsightCourse;
import com.pluralsight.courseinfo.domain.Course;
import com.pluralsight.courseinfo.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Predicate;

public class CourseRetriever {
    private static final Logger LOG = LoggerFactory.getLogger(CourseRetriever.class);

    public static void main(String... args) {
        LOG.info("CourseRetriever starting");
        if (args.length == 0) {
            LOG.warn("Please provide an author name as first argument");
            return;
        }

        try {
            retrieveCourse(args[0]);
        } catch (Exception e) {
            LOG.error("Unexpected error occurred", e);
            e.printStackTrace();
        }

    }

    private static void retrieveCourse(String authorID) {
        LOG.info("Retrieving courses for author '{}'", authorID);

        CourseRetrievelService courseRetrievelService = new CourseRetrievelService();
        List<PluralsightCourse> coursesToStore = courseRetrievelService.getCourseFor(authorID)
                .stream()
                //.filter(course -> course.isRetired())
                .filter(Predicate.not(PluralsightCourse::isRetired))
                .toList();
        LOG.info("Retrieve the following {} courses '{}'", coursesToStore.size(), coursesToStore);

        CourseRepository courseRepository = CourseRepository.openRepository("./courses.db");
        CourseStorageService courseStorageService = new CourseStorageService(courseRepository);
        courseStorageService.storePluralsightCourses(coursesToStore);
        LOG.info("Courses successfully stored!");

        /*var list = courseRepository.getAllCourses();
        for (Course course: list)
            System.out.println(course);*/

    }
}
