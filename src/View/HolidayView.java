package View;

import Model.HolidayModel.HolidayType;
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
  public JButton refreshBtn = new JButton("Refresh");
  public JButton exportBtn = new JButton("Export");

  public JComboBox<String> employeeField = new JComboBox<>();
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
    table.getTableHeader().setReorderingAllowed(false);

    table.setModel(tableModel);

    buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    buttonsPanel.add(addBtn);
    buttonsPanel.add(deleteBtn);
    buttonsPanel.add(updateBtn);
    buttonsPanel.add(refreshBtn);
    buttonsPanel.add(exportBtn);
  }

  public int getEid() {
    return Integer.parseInt(employeeField.getSelectedItem().toString().split("-")[0].trim());
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

  public void showSuccess(String message) {
    JOptionPane.showMessageDialog(null, message, null, JOptionPane.INFORMATION_MESSAGE);
  }

  public void showFailure(String message) {
    JOptionPane.showMessageDialog(null, message, null, JOptionPane.ERROR_MESSAGE);
  }
}
