package others;

import model.Component;
import model.Course;
import model.Semester;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class AddCourse {
    public AddCourse() throws IOException, ParseException {
        Scanner myObj = new Scanner(System.in);
        Course course = newCourse();
        System.out.println("How many grading components does " + course.getCourseName() + " have?");
        int cNum = myObj.nextInt();
        for (int i = 0; i < cNum; i++) {
            Component com = newComponent();
            course.addComponent(com);
        }
        Boolean valid = percentageValid(course.getComponents());
        if (valid) {
            System.out.println(course.getCourseName());
            for (Component c : course.getComponents()) {
                System.out.println(c.getName() + ": " + c.getMarksOutOfInPercentage());
            }
            System.out.println("[1]save");
            System.out.println("[2]cancel");
            int nextLine = myObj.nextInt();

            while (nextLine != 1 && nextLine != 2) {
                System.out.println("Sorry, Please try again.");
                nextLine = myObj.nextInt();
            }
            if (nextLine == 1) {
                new SaveCourse(course);

            } else if (nextLine == 2) {
                new Main();
            }
        }else{
            System.out.println("Please try again and make sure the percentages add up to 1.");
            new AddCourse();
        }

    }

    public Component newComponent() {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Please name one component.");
        String componentName = myObj.nextLine();
        System.out.println("Please enter the percentage it weighted.");
        Double componentPercentage = myObj.nextDouble();
        Component com = new Component(componentName, componentPercentage);
        return com;
    }

    public Course newCourse() {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Please enter a course number.");
        String courseName = myObj.nextLine();
        Course course = new Course(courseName, new Semester(""));
        return course;
    }

    public Boolean percentageValid(List<Component> componentList) {
        Double totalPercentage = 0.0;
        for (Component c : componentList) {
            totalPercentage += c.getMarksOutOfInPercentage();
        }


        return (totalPercentage == 1);
    }
}
