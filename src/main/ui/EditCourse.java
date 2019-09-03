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

        Double totalMarksYouGetInPercentage = 0.0;
        Double totalMarksOutOfInPercentage = 0.0;

        for(Component component: course.getComponents()){
            String name = component.getName();

            Double marksOutOfInPercentage = component.getMarksOutOfInPercentage();
            Double marksYouGet = component.getMarksYouGet();
            Double marksOutOf = component.getMarksOutOf();

            Double percentage = marksYouGet/marksOutOf*100;
            Double marksYouGetInPercentage = percentage/100*marksOutOfInPercentage;

            totalMarksYouGetInPercentage += marksYouGetInPercentage;
            totalMarksOutOfInPercentage += marksOutOfInPercentage;


            defaultTableModel.addRow( new Object[]{name,marksYouGet,marksOutOf,percentage,marksYouGetInPercentage,marksOutOfInPercentage});
        }

        defaultTableModel.addRow( new Object[]{"Total", null, null, null, totalMarksYouGetInPercentage, totalMarksOutOfInPercentage});




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

                    Double marksOutOfInPercentage = Double.valueOf(defaultTableModel.getValueAt(i,5).toString());
                    Double marksYouGet = Double.valueOf(defaultTableModel.getValueAt(i,1).toString());
                    Double marksOutOf = Double.valueOf(defaultTableModel.getValueAt(i,2).toString());
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
