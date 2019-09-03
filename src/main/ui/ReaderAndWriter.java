package ui;

import model.Course;
import model.SemesterManagement;

import java.util.ArrayList;
import java.util.List;

public interface ReaderAndWriter {
    SemesterManagement SEMESTER_MANAGEMENT = new SemesterManagement();
    List<Course> courses = new ArrayList<>();


}
