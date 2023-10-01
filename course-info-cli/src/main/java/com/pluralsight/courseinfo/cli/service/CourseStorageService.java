package com.pluralsight.courseinfo.cli.service;

import com.pluralsight.courseinfo.domain.Course;
import com.pluralsight.courseinfo.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

public class CourseStorageService {
    private static final String PS_BASE_URL = "https://app.pluralsight.com";
    private final CourseRepository courseRepository;

    public CourseStorageService(CourseRepository repository) {
        courseRepository = repository;
    }

    public void storePluralsightCourses(List<PluralsightCourse> psCourses) {
        for (PluralsightCourse psCourse: psCourses) {
            Course course = new Course(
                    psCourse.id(),
                    psCourse.title(),
                    psCourse.durationToMinutes(),
                    (PS_BASE_URL + psCourse.contentUrl())
            );
            courseRepository.saveCourse(course);
        }
    }
}
