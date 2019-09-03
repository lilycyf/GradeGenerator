package ui;

import model.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Writer{
    Writer(CourseManagement cm, SemesterManagement sm) throws FileNotFoundException {
        writeCourses(cm);
        writeSemesters(sm);
    }
    Writer(CourseManagement cm) throws FileNotFoundException {
        writeCourses(cm);
    }

    public void writeSemesters(SemesterManagement sm) throws FileNotFoundException {

        Map<String, List<String>> mapSemesters = new HashMap<>();
        for (Semester s: sm.getSemesters()) {
            mapSemesters.put(s.getYear(),s.getCourses());
        }

        JSONObject objSemesters = new JSONObject(mapSemesters);

        PrintWriter writer = new PrintWriter("Semesters.json");
        writer.write(objSemesters.toJSONString());
        writer.close();

    }
    public void writeCourses(CourseManagement cm) throws FileNotFoundException {

        Map<String, Object> mapCourses = new HashMap<>();
        for (Course c: cm.getCourses()) {
            Map<String, Object> mapCourse = new HashMap<>();
            mapCourse.put("Semester",c.getSemester().getYear());

            JSONArray arrayComponents = new JSONArray();



            for (Component com: c.getComponents()){
                Map<String, Object> mapComponent = new HashMap<>();
                mapComponent.put("Name",com.getName());
                mapComponent.put("MarksYouGet",com.getMarksYouGet());
                mapComponent.put("MarksOutOf",com.getMarksOutOf());
                mapComponent.put("Percentage",com.getMarksOutOfInPercentage());

                JSONObject objComponent = new JSONObject(mapComponent);

                arrayComponents.add(objComponent);
            }
            mapCourse.put("Components",arrayComponents);
            JSONObject objCourse = new JSONObject(mapCourse);
            mapCourses.put(c.getCourseName(), objCourse);
        }

        JSONObject objCourses = new JSONObject(mapCourses);
        PrintWriter writer = new PrintWriter("Courses.json");
        writer.write(objCourses.toJSONString());
        writer.close();

    }
}
