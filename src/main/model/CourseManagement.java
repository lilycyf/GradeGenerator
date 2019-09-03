package model;

import java.util.ArrayList;
import java.util.List;

public class CourseManagement {
    private List<Course> courses = new ArrayList<>();

    public List<Course> getCourses() {
        return courses;
    }

    public CourseManagement(){

    }
    public void addCourse(Course c){
        if(!courses.contains(c)){
            courses.add(c);
        }
    }

}
