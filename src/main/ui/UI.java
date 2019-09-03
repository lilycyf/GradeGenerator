package ui;

import model.CourseManagement;
import model.SemesterManagement;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class UI {
    public static void main(String[] args) throws IOException, ParseException {

        CourseManagement cm = new CourseManagement();
        SemesterManagement sm = new SemesterManagement();
        new Reader(cm, sm);

        new Window(cm, sm);
    }

}
