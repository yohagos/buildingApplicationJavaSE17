package com.pluralsight.courseinfo.cli;

public class CourseRetriever {

    public static void main(String... args) {
        System.out.println("CourseRetriever started");
        if (args.length == 0) {
            System.out.println("Please provide an author name as first argument");
            return;
        }

        try {
            retrieveCourse(args[0]);
        } catch (Exception e) {
            System.out.println("Unexpected error occurred");
            e.printStackTrace();
        }

    }

    private static void retrieveCourse(String authorID) {
        System.out.println("Retrieving courses for author " + authorID);
    }
}
