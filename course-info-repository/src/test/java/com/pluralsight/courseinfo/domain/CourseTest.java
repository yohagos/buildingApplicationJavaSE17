package com.pluralsight.courseinfo.domain;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CourseTest {

    @Test
    void rejectNullComponents() {
        assertThrows(IllegalArgumentException.class, () ->
                new Course(null, null, 1, null, Optional.empty()));
    }

    @Test
    void rejectBlankNotes() {
        assertThrows(IllegalArgumentException.class, () ->
                new Course("1", "title", 1, "url", Optional.of("")));
    }

    /*
    // Wrong approach

    @BeforeEach
    void setCourse() {
        course = new Course("id", "name", 0, "url");
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
            "id", "id"
            "name", "name"
            "", "url"
            """)
    void CheckFilledCourseFields(String input, String expected) {
        assertEquals(input, expected);
    }

    String filled(String s) {
        if (s == null || s.isBlank())
            throw new IllegalArgumentException("Empty string");
        return s;
    }*/
}
