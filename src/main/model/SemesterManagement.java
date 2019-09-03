package model;

import java.util.ArrayList;
import java.util.List;

public class SemesterManagement {
    private List<Semester> semesters = new ArrayList<>();



    public SemesterManagement(){
    }

    public List<Semester> getSemesters() {
        return semesters;
    }

    public void addCourse(Course course){
        if(!semesters.contains(course.getSemester())){
            semesters.add(course.getSemester());
        }
        else{
            for(Semester s:semesters){
                if(course.getSemester().equals(s)){
                    s.addCourse(course);
                }
            }
        }

    }
}
