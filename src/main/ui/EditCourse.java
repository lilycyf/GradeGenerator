package ui;

import model.Component;
import model.Course;
import model.CourseManagement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;

public class EditCourse extends JFrame {
    private JTable table;


    public EditCourse(final Course course, final CourseManagement cm) {

        setTitle(course.getCourseName());

        setVisible(true);

        setBounds(200, 100, 800, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        table = new JTable();
        final DefaultTableModel defaultTableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                if(column == 3||column == 4){
                    return false;
                }else if(row == getRowCount()-1) {
                    return false;
                }else {
                    return true;
                }
            }
        };
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
        table.getColumnModel().getColumn(0).setPreferredWidth(70);
        table.getColumnModel().getColumn(1).setPreferredWidth(50);
        table.getColumnModel().getColumn(2).setPreferredWidth(50);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        table.getColumnModel().getColumn(4).setPreferredWidth(140);
        table.getColumnModel().getColumn(5).setPreferredWidth(140);

        JTableHeader jTableHeader = table.getTableHeader();
        jTableHeader.setResizingAllowed(false);
        jTableHeader.setReorderingAllowed(false);



        final TableModel tableModel = table.getModel();

        tableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    if((e.getColumn() == 1||e.getColumn() == 2||e.getColumn() == 5)&&(e.getFirstRow()!=defaultTableModel.getRowCount()-1)) {
                        int row = e.getFirstRow();
                        Double marksOutOfInPercentage = Double.valueOf(defaultTableModel.getValueAt(row, 5).toString());
                        Double marksYouGet = Double.valueOf(defaultTableModel.getValueAt(row, 1).toString());
                        Double marksOutOf = Double.valueOf(defaultTableModel.getValueAt(row, 2).toString());

                        Double percentage = marksYouGet / marksOutOf * 100;
                        Double marksYouGetInPercentage = percentage / 100 * marksOutOfInPercentage;

                        tableModel.setValueAt(percentage, row, 3);
                        tableModel.setValueAt(marksYouGetInPercentage, row, 4);

                        resetTotal(defaultTableModel);
                    }
                }
            }
        });



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
        btnSave.setBounds(600, 200, 150, 25);
        contentPane.add(btnSave);

        JButton btnAddComponent = new JButton("Add Component");
        btnAddComponent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                defaultTableModel.insertRow((defaultTableModel.getRowCount()-1), new Object[]{"new Component", 1.0, 1.0, 100.0, 100.0, 100.0});
                resetTotal(defaultTableModel);
            }
        });
        btnAddComponent.setBounds(600, 140, 150, 25);
        contentPane.add(btnAddComponent);

        JButton btnRemoveComponent = new JButton("Remove Component");
        btnRemoveComponent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                if(table.getSelectedRow()!= table.getRowCount()-1 && table.getSelectedRow()!= -1) {
                    defaultTableModel.removeRow(table.getSelectedRow());
                    resetTotal(defaultTableModel);
                }
            }
        });
        btnRemoveComponent.setBounds(600, 170, 150, 25);
        contentPane.add(btnRemoveComponent);

        SwingUtilities.updateComponentTreeUI(this);
    }

    private void resetTotal(DefaultTableModel defaultTableModel){
        Double totalMarksYouGetInPercentage = 0.0;
        Double totalMarksOutOfInPercentage = 0.0;
        for(int i = 0; i<defaultTableModel.getRowCount()-1; i++){
            totalMarksYouGetInPercentage += Double.valueOf(defaultTableModel.getValueAt(i,4).toString());
            totalMarksOutOfInPercentage += Double.valueOf(defaultTableModel.getValueAt(i,5).toString());
        }
        defaultTableModel.setValueAt(totalMarksYouGetInPercentage, defaultTableModel.getRowCount()-1, 4);
        defaultTableModel.setValueAt(totalMarksOutOfInPercentage, defaultTableModel.getRowCount()-1, 5);
    }





}
