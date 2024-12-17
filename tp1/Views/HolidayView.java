package Views;

import Models.HolidayType;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class HolidayView extends JPanel {
  private JPanel inputsPanel = new JPanel();
  private JPanel tablePanel = new JPanel();
  private JPanel buttonsPanel = new JPanel();

  public JButton addBtn = new JButton("Add");
  public JButton deleteBtn = new JButton("Delete");
  public JButton updateBtn = new JButton("Update");

  public JTextField employeeField = new JTextField();
  public JComboBox<HolidayType> typeField = new JComboBox<>(HolidayType.values());
  public JTextField startDateField = new JTextField();
  public JTextField endDateField = new JTextField();

  public JTable table = new JTable();
  public DefaultTableModel tableModel = new DefaultTableModel();

  public HolidayView() {
    this.setLayout(new BorderLayout());
    this.add(inputsPanel, BorderLayout.NORTH);
    this.add(tablePanel, BorderLayout.CENTER);
    this.add(buttonsPanel, BorderLayout.SOUTH);

    inputsPanel.setLayout(new GridLayout(4, 2));
    inputsPanel.add(new JLabel("Employee ID"));
    inputsPanel.add(employeeField);
    inputsPanel.add(new JLabel("Type"));
    inputsPanel.add(typeField);
    inputsPanel.add(new JLabel("Start date"));
    inputsPanel.add(startDateField);
    inputsPanel.add(new JLabel("End date"));
    inputsPanel.add(endDateField);

    tablePanel.setLayout(new BorderLayout());
    tablePanel.add(new JScrollPane(table));
    tableModel.addColumn("Holiday Id");
    tableModel.addColumn("Employee Id");
    tableModel.addColumn("Full Name");
    tableModel.addColumn("Start Date");
    tableModel.addColumn("End Date");
    tableModel.addColumn("Type");
    table.setModel(tableModel);

    buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    buttonsPanel.add(addBtn);
    buttonsPanel.add(deleteBtn);
    buttonsPanel.add(updateBtn);
  }

  public int getEid() {
    return Integer.parseInt(employeeField.getText());
  }

  public String getType() {
    return typeField.getSelectedItem().toString();
  }

  public String getStartDate() {
    return startDateField.getText();
  }

  public String getEndDate() {
    return endDateField.getText();
  }
}
