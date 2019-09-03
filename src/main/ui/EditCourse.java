package ui;

import model.Component;
import model.Course;
import model.CourseManagement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;

public class EditCourse extends JFrame {
    public EditCourse(final Course course, final CourseManagement cm) {

        setTitle(course.getCourseName());

        setVisible(true);

        setBounds(100, 100, 800, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JTable table = new JTable();
        final DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("Component");
        defaultTableModel.addColumn("marks you get");
        defaultTableModel.addColumn("marks out of");
        defaultTableModel.addColumn("percentage");
        defaultTableModel.addColumn("marks you get in percentage");
        defaultTableModel.addColumn("marks out of in percentage");
        defaultTableModel.addRow( new Object[]{"Component1", "3", "5", "60%", "48%", "70%"});
        defaultTableModel.addRow( new Object[]{"Component2", "4", "5", "80%", "24%", "30%"});
        defaultTableModel.addRow( new Object[]{"Total", null, null, null, "72%", "100%"});




        table.setModel(defaultTableModel);

        table.setBounds(12, 13, 750, 200);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(50);
        table.getColumnModel().getColumn(2).setPreferredWidth(50);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        table.getColumnModel().getColumn(4).setPreferredWidth(140);
        table.getColumnModel().getColumn(5).setPreferredWidth(140);


        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(12, 13, 750, 100);
        contentPane.add(scrollPane);

        JButton btnSave = new JButton("Save");
        btnSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                int rowCount = defaultTableModel.getRowCount() - 1;
                course.clearComponent();

                for(int i = 0; i < rowCount; i++){
                    String name = (String) defaultTableModel.getValueAt(i,0);
                    String mooInPercentage = (String)defaultTableModel.getValueAt(i,5);
                    Double marksOutOfInPercentage = Double.valueOf(mooInPercentage.substring(0, mooInPercentage.lastIndexOf("%")));
                    Double marksYouGet = Double.valueOf((String)defaultTableModel.getValueAt(i,1));
                    Double marksOutOf = Double.valueOf((String)defaultTableModel.getValueAt(i,2));
                    Component component = new Component(name, marksOutOfInPercentage);
                    component.setMarksOutOf(marksOutOf);
                    component.setMarksYouGet(marksYouGet);
                    course.addComponent(component);
                }
                try {
                    new Writer(cm);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        btnSave.setBounds(600, 186, 114, 25);
        contentPane.add(btnSave);

        SwingUtilities.updateComponentTreeUI(this);
    }


}
