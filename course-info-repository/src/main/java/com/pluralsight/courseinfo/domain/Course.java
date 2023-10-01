package com.pluralsight.courseinfo.domain;

import java.util.Optional;

public record Course(String id, String name, long length, String url) {

    public Course {
        filled(id);
        filled(name);
        filled(url);
    }

    private static void filled(String s) {
        if (s == null || s.isBlank())
            throw new IllegalArgumentException("No value present!");
    }

    /*@Override
    public String toString() {
        return "Course: \nid -> " + id() + "\nname -> " + name() + "\nlength in minutes -> " + length() + "\nurl -> " + url();
    }*/
}
