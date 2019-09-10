package ui;

import model.CourseManagement;
import model.SemesterManagement;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UI {
    public static void main(String[] args) throws IOException, ParseException {

        CourseManagement cm = new CourseManagement();
        SemesterManagement sm = new SemesterManagement();
        try {
            new Reader(cm, sm);
        } catch (FileNotFoundException e) {
            new File("Courses.json");
            new File("Semesters.json");
        }
        new Window(cm, sm);
    }

}
