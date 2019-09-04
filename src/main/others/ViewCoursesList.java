package others;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class ViewCoursesList {
    public ViewCoursesList() throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader("Courses.json"));

        Map<String, JSONArray> map = (JSONObject)obj;

        for (Map.Entry<String, JSONArray> keySet : map.entrySet()) {
            System.out.println(keySet.getKey() + ":");
            JSONArray courses = keySet.getValue();

            Iterator iterator = courses.iterator();
            while (iterator.hasNext()) {
                JSONObject next = (JSONObject) iterator.next();

                String Name = (String) next.get("Name");
                Double Percentage = (Double) next.get("Percentage");
                System.out.println(Name + ": " + Percentage);

            }
        }

        Scanner myObj = new Scanner(System.in);

        System.out.println("[1]add course");
        System.out.println("[2]edit courses");
        int nextLine = myObj.nextInt();

        while (nextLine != 1 && nextLine != 2) {
            System.out.println("Sorry, Please try again.");
            nextLine = myObj.nextInt();
        }
        if (nextLine == 1) {
            new AddCourse();

        } else if (nextLine == 2) {

        }
    }
}
