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

public class Window extends JFrame {
    private JComboBox cobCourses;

    public Window(final CourseManagement cm, final SemesterManagement sm){

        setTitle("GradeGenerator");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBounds(100, 100, 354, 342);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        final JComboBox cobSemesters = new JComboBox();
        cobSemesters.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED)
                {
                    DefaultComboBoxModel dcbmCourses = new DefaultComboBoxModel();
                    if(cobSemesters.getSelectedItem().equals("All")){
                        for(Course c: cm.getCourses()){
                            dcbmCourses.addElement(c.getCourseName());
                        }
                    }else {
                        for (Course c : cm.getCourses()) {
                            if (c.getSemester().getYear().equals(cobSemesters.getSelectedItem())) {
                                dcbmCourses.addElement(c.getCourseName());
                            }
                        }
                    }
                    cobCourses.setModel(dcbmCourses);
                }
            }
        });
        cobSemesters.setBounds(39, 112, 114, 22);
        DefaultComboBoxModel dcbmSemesters = new DefaultComboBoxModel();
        dcbmSemesters.addElement("All");
        for(Semester s: sm.getSemesters()){
            dcbmSemesters.addElement(s.getYear());
        }
        cobSemesters.setModel(dcbmSemesters);
        contentPane.add(cobSemesters);

        cobCourses = new JComboBox();
        cobCourses.setBounds(179, 112, 114, 22);
        DefaultComboBoxModel dcbmCourses = new DefaultComboBoxModel();
        for(Course c: cm.getCourses()){
            dcbmCourses.addElement(c.getCourseName());
        }
        cobCourses.setModel(dcbmCourses);
        contentPane.add(cobCourses);

        JLabel lblSemester = new JLabel("Semester");
        lblSemester.setBounds(39, 83, 56, 16);
        contentPane.add(lblSemester);

        JLabel lblCourse = new JLabel("Course");
        lblCourse.setBounds(179, 83, 56, 16);
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
        btnGo.setBounds(179, 186, 114, 25);
        contentPane.add(btnGo);

        JButton btnAddCourse = new JButton("Add Course");
        btnAddCourse.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                new Add(cm, sm);
            }
        });
        btnAddCourse.setBounds(39, 186, 114, 25);
        contentPane.add(btnAddCourse);

        SwingUtilities.updateComponentTreeUI(this);
    }

}
