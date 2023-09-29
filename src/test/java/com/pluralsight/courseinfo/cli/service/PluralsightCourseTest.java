package com.pluralsight.courseinfo.cli.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class PluralsightCourseTest {

    @ParameterizedTest
    @CsvSource(textBlock = """
            01:08:37.96482, 68
            00:05:36, 5
            00:00:10, 0
            """)
    void durationToMinutes(String input, long expected) {
        PluralsightCourse course =
                new PluralsightCourse("id", "title", input, "url", false);
        assertEquals(expected, course.durationToMinutes());
    }

    /*@Test
    void durationToMinutesOverHour() {
        PluralsightCourse course =
                new PluralsightCourse("id", "title", "01:05:37.961333", "url", false);
        assertEquals(65, course.durationToMinutes());
    }

    @Test
    void durationToMinutesZero() {
        PluralsightCourse course =
                new PluralsightCourse("id", "title", "00:00:37", "url", false);
        assertEquals(0, course.durationToMinutes());
    }*/
}