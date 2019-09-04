package ui;

import model.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;

public class Add extends JFrame {
    JLabel lblSuccess;

    public Add(final CourseManagement cm, final SemesterManagement sm, final JComboBox cc, final JComboBox cs){

        setTitle("Add Course");

        setVisible(true);

        setBounds(200, 100, 300, 301);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        final JTextField txtSemester = new JTextField();
        txtSemester.setText("2019W1");
        txtSemester.setToolTipText("");
        txtSemester.setBounds(115, 37, 116, 22);
        contentPane.add(txtSemester);
        txtSemester.setColumns(10);

        final JTextField txtCourseNum = new JTextField();
        txtCourseNum.setText("CPSC110");
        txtCourseNum.setBounds(115, 79, 116, 22);
        contentPane.add(txtCourseNum);
        txtCourseNum.setColumns(10);

        JLabel lblSemEster = new JLabel("Semester");
        lblSemEster.setBounds(20, 40, 56, 16);
        contentPane.add(lblSemEster);

        JLabel lblCourseNum = new JLabel("Course Num");
        lblCourseNum.setBounds(20, 82, 75, 16);
        contentPane.add(lblCourseNum);

        lblSuccess = new JLabel("Course");
        lblSuccess.setBounds(20, 180, 250, 16);
        contentPane.add(lblSuccess);
        lblSuccess.setVisible(false);

        JButton btnAdd = new JButton("Add");
        btnAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                Semester s = new Semester(txtSemester.getText());
                Course c = new Course(txtCourseNum.getText(),s);

                if(!cm.getCourses().contains(c)) {
                    sm.addCourse(c);
                    cm.addCourse(c);
                    lblSuccess.setText(c.getCourseName() +" have been added successfully!");
                    lblSuccess.setVisible(true);
                }else {
                    lblSuccess.setText(c.getCourseName() +" is ready in the system.");
                    lblSuccess.setVisible(true);
                }

                try {
                    new Writer(cm, sm);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                renewCobSemesters(sm, cs);
                renewCobCourses(cm, cc, cs);



            }
        });
        btnAdd.setBounds(150, 130, 97, 25);
        contentPane.add(btnAdd);



        SwingUtilities.updateComponentTreeUI(this);
    }

    public void renewCobSemesters(SemesterManagement sm, JComboBox cs){
        DefaultComboBoxModel dcbmSemesters = new DefaultComboBoxModel();
        dcbmSemesters.addElement("All");
        for(Semester s: sm.getSemesters()){
            dcbmSemesters.addElement(s.getYear());
        }
        cs.setModel(dcbmSemesters);
    }

    public void renewCobCourses(CourseManagement cm, JComboBox cc, JComboBox cs) {
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
