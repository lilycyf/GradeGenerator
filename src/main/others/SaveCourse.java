package others;

import model.Component;
import model.Course;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

public class SaveCourse {
    public SaveCourse(Course course) throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader("Courses.json"));
        JSONObject jo = (JSONObject) obj;


        JSONArray ja = new JSONArray();


        Map m;

        for (Component c : course.getComponents()) {
            m = new LinkedHashMap();
            m.put("Name", c.getName());
            m.put("Percentage", c.getMarksOutOfInPercentage());
            ja.add(m);
        }

        jo.put(course.getCourseName(), ja);

        PrintWriter pw = new PrintWriter("Courses.json");
        pw.write(jo.toJSONString());

        pw.close();

        new Main();

    }
}
