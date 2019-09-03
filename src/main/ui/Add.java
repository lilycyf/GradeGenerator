package ui;

import model.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;

public class Add extends JFrame implements ReaderAndWriter{
    public Add(final CourseManagement cm, final SemesterManagement sm){

        setVisible(true);

        setBounds(100, 100, 278, 301);
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
        lblSemEster.setBounds(28, 40, 56, 16);
        contentPane.add(lblSemEster);

        JLabel lblCourseNum = new JLabel("Course Num");
        lblCourseNum.setBounds(28, 82, 75, 16);
        contentPane.add(lblCourseNum);

        JButton btnEnter = new JButton("Enter");
        btnEnter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                Semester s = new Semester(txtSemester.getText());
                Course c = new Course(txtCourseNum.getText(),s);

                if(!cm.getCourses().contains(c)) {
                    sm.addCourse(c);
                    cm.addCourse(c);
                }

                try {
                    new Writer(cm, sm);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        btnEnter.setBounds(134, 130, 97, 25);
        contentPane.add(btnEnter);


        SwingUtilities.updateComponentTreeUI(this);
    }
}
