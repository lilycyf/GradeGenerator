package ui;

import model.Course;
import model.CourseManagement;
import model.Semester;
import model.SemesterManagement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;

public class Window extends JFrame {
    private JComboBox cobCourses;

    public Window(final CourseManagement cm, final SemesterManagement sm){

        setTitle("Grade Generator");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBounds(200, 100, 370, 342);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        final JComboBox cobSemesters = new JComboBox();
        cobSemesters.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED)
                {
                    renewCobCourses(cm, cobCourses, cobSemesters);
                }
            }
        });
        cobSemesters.setBounds(40, 112, 130, 22);
        renewCobSemesters(sm, cobSemesters);
        contentPane.add(cobSemesters);

        cobCourses = new JComboBox();
        cobCourses.setBounds(190, 112, 130, 22);
        renewCobCourses(cm, cobCourses, cobSemesters);
        contentPane.add(cobCourses);

        JLabel lblSemester = new JLabel("Semester");
        lblSemester.setBounds(40, 83, 56, 16);
        contentPane.add(lblSemester);

        JLabel lblCourse = new JLabel("Course");
        lblCourse.setBounds(190, 83, 56, 16);
        contentPane.add(lblCourse);

        JButton btnGo = new JButton("Go");
        btnGo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                for(Course c:cm.getCourses()){
                    if(cobCourses.getSelectedItem().equals(c.getCourseName())){
                        new EditCourse(c, cm);
                    }
                }

            }
        });
        btnGo.setBounds(190, 186, 130, 25);
        contentPane.add(btnGo);

        JButton btnAddCourse = new JButton("Add Course");
        btnAddCourse.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                new Add(cm, sm, cobCourses, cobSemesters);
            }
        });
        btnAddCourse.setBounds(40, 186, 130, 25);
        contentPane.add(btnAddCourse);

        JButton btnRemoveCourse = new JButton("Remove Course");
        btnRemoveCourse.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                Course course = null;
                for(Course c:cm.getCourses()){
                    if(cobCourses.getSelectedItem().equals(c.getCourseName())){
                        course = new Course(c.getCourseName(),c.getSemester());

                    }
                }                        
                sm.removeCourse(course);
                cm.removeCourse(course);
                try {
                    new Writer(cm,sm);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                renewCobSemesters(sm, cobSemesters);
                renewCobCourses(cm, cobCourses, cobSemesters);

            }
        });
        btnRemoveCourse.setBounds(40, 230, 130, 25);
        contentPane.add(btnRemoveCourse);

        SwingUtilities.updateComponentTreeUI(this);
    }

    private void renewCobSemesters(SemesterManagement sm, JComboBox cs){
        DefaultComboBoxModel dcbmSemesters = new DefaultComboBoxModel();
        dcbmSemesters.addElement("All");
        for(Semester s: sm.getSemesters()){
            dcbmSemesters.addElement(s.getYear());
        }
        cs.setModel(dcbmSemesters);
    }

    private void renewCobCourses(CourseManagement cm, JComboBox cc, JComboBox cs) {
        DefaultComboBoxModel dcbmCourses = new DefaultComboBoxModel();
        if(cs.getSelectedItem().equals("All")){
            for(Course c: cm.getCourses()){
                dcbmCourses.addElement(c.getCourseName());
            }
        }else {
            for (Course c : cm.getCourses()) {
                if (c.getSemester().getYear().equals(cs.getSelectedItem())) {
                    dcbmCourses.addElement(c.getCourseName());
                }
            }
        }
        cc.setModel(dcbmCourses);
    }

}
