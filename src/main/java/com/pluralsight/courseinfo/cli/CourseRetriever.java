package com.pluralsight.courseinfo.cli;

import com.pluralsight.courseinfo.cli.service.CourseRetrievelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        String coursesToStore = courseRetrievelService.getCourseFor(authorID);
        LOG.info("Retrieve the following courses '{}'", coursesToStore);

    }
}
