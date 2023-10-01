package com.pluralsight.courseinfo.repository;

import com.pluralsight.courseinfo.domain.Course;
import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

class CourseJdbcRepository implements CourseRepository{

    private static final String H2_DATABASE_URL =
            "jdbc:h2:file:%s;AUTO_SERVER=TRUE;INIT=RUNSCRIPT FROM './db_init.sql'";
    private static final String GET_ALL = "SELECT * FROM COURSES";
    private static final String INSERT_COURSE = """
            MERGE INTO Courses (id, name, length, url)
            VALUES (?,?,?,?)
            """;
    private final DataSource dataSource;

    public CourseJdbcRepository(String databaseFile) {
        JdbcDataSource jdbcDataSource = new JdbcDataSource();
        jdbcDataSource.setURL(H2_DATABASE_URL.formatted(databaseFile));
        this.dataSource = jdbcDataSource;
    }

    @Override
    public void saveCourse (Course course) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COURSE);
            preparedStatement.setString(1, course.id());
            preparedStatement.setString(2, course.name());
            preparedStatement.setLong(3, course.length());
            preparedStatement.setString(4, course.url());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RepositoryException("Failed to save "+course, e);
        }
    }

    @Override
    public List<Course> getAllCourses() {
        try (Connection connection = dataSource.getConnection()) {
            /*PreparedStatement statement = connection.prepareStatement(GET_ALL);
            var result =  statement.executeQuery();
            List<Course> courses = new ArrayList<>();
            while(result.next()) {
                courses.add(new Course(result.getString("id"), result.getString("name"), result.getLong("length"), result.getString("url"), result.getObject(5, Optional.class)));
            }
            return courses;*/
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            List<Course> courses = new ArrayList<>();
            while(resultSet.next()){
                Course course = new Course(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getLong(3),
                        resultSet.getString(4)
                );
                courses.add(course);
            }
            return Collections.unmodifiableList(courses);
        } catch (SQLException e) {
            throw new RepositoryException("Failed to retrieve courses : ", e);
        }
    }
}
