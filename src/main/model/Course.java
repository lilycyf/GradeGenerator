package model;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String courseName;
    private List<Component> components = new ArrayList<>();


    public Course(String courseName) {
        this.courseName = courseName;
    }

    public List<Component> getComponents(){
        return components;
    }

    public void addComponent(Component c){
        components.add(c);
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
