package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Course {
    private String courseName;
    private List<Component> components = new ArrayList<>();
    private Semester semester;



    public Course(String courseName, Semester semester) {
        this.courseName = courseName;
        this.semester = semester;
        semester.addCourse(this);
    }

    public void setSemester(Semester semester) {
        if (!this.semester.equals(semester)){
            this.semester = semester;
            semester.addCourse(this);
        }
    }

    public Semester getSemester() {
        return semester;
    }

    public List<Component> getComponents(){
        return components;
    }

    public void addComponent(Component c){
        components.add(c);
    }

    public void clearComponent(){
        components.clear();
    }

    public String getCourseName() {
        return courseName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(courseName, course.courseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseName);
    }


}
