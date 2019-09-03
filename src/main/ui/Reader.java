package ui;

import model.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Reader{

    Reader(CourseManagement cm, SemesterManagement sm) throws IOException, ParseException {
        readCourses(cm);
        readSemesters(sm);
    }

    public void readSemesters(SemesterManagement sm) throws IOException, ParseException {
        Object objSemesters = new JSONParser().parse(new FileReader("Semesters.json"));

        Map<String, List<String>> mapSemesters = (JSONObject)objSemesters;

        for (Map.Entry<String, List<String>> keySet: mapSemesters.entrySet()) {
            Semester semester = new Semester(keySet.getKey());
            List<String> courses = keySet.getValue();
            for(String c: courses){
                sm.addCourse(new Course(c, semester));
            }
        }

    }
    public void readCourses(CourseManagement cm) throws IOException, ParseException {
        Object objCourses = new JSONParser().parse(new FileReader("Courses.json"));
        Map<String, Object> mapCourses = (JSONObject)objCourses;

        for (Map.Entry<String, Object> keySet: mapCourses.entrySet()) {
            JSONObject objCourse = (JSONObject) keySet.getValue();

            Semester semester = new Semester((String) objCourse.get("Semester"));

            Course course = new Course(keySet.getKey(),semester);
            JSONArray mapComponents = (JSONArray) objCourse.get("Components");

            for(int i=0; i < mapComponents.size(); i++) {
                JSONObject com = (JSONObject) mapComponents.get(i);
                Component component = new Component((String)com.get("Name"),(Double)com.get("Percentage"));
                component.setMarksYouGet((Double) com.get("MarksYouGet"));
                component.setMarksOutOf((Double) com.get("MarksOutOf"));
                course.addComponent(component);
            };

            cm.addCourse(course);
        }

    }


}
