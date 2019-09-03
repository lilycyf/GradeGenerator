package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Semester {
    private List<String> courses = new ArrayList<>();
    private String year;


    public Semester(String year){
        this.year = year;
    }

    public void addCourse(Course course){
        if (!courses.contains(course.getCourseName())) {
            courses.add(course.getCourseName());
            course.setSemester(this);
        }

    }

    public List<String> getCourses() {
        return courses;
    }

    public String getYear() {
        return year;
    }

    public void deleteCourse(Course course){
        courses.remove(course.getCourseName());

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Semester semester = (Semester) o;
        return year.equals(semester.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year);
    }
}
