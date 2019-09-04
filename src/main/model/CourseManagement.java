package model;

import java.util.ArrayList;
import java.util.List;

public class CourseManagement {
    private List<Course> courses = new ArrayList<>();

    public CourseManagement(){

    }

    public List<Course> getCourses() {
        return courses;
    }


    public void addCourse(Course c){
        if(!courses.contains(c)){
            courses.add(c);
        }
    }

    public void removeCourse(Course c){
        courses.remove(c);

    }

}
