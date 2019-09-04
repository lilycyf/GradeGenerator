package Others;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;

public class Main{
    public Main() throws IOException, ParseException {
        Scanner myObj = new Scanner(System.in);
        System.out.println("[1]add course");
        System.out.println("[2]view the course list");


        int nextLine = myObj.nextInt();
        while (nextLine != 1&& nextLine !=2){
            System.out.println("Sorry, Please try again.");
            nextLine = myObj.nextInt();
        }
        if(nextLine == 1){
            new AddCourse();
        }
        if(nextLine == 2){
            new ViewCoursesList();
        }
    }
}
