package View;

import Model.EmployeeModel.Post;
import Model.EmployeeModel.Role;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EmployeeView extends JPanel {

  private JPanel inputsPanel = new JPanel();
  private JPanel tablePanel = new JPanel();
  private JPanel buttonsPanel = new JPanel();

  public JButton addBtn = new JButton("Add");
  public JButton deleteBtn = new JButton("Delete");
  public JButton updateBtn = new JButton("Update");
  public JButton showBtn = new JButton("Show");
  public JButton fillBtn = new JButton("Fill");
  public JButton importBtn = new JButton("Import");
  public JButton exportBtn = new JButton("Export");

  public JTextField lnameField = new JTextField();
  public JTextField fnameField = new JTextField();
  public JTextField emailField = new JTextField();
  public JTextField phoneField = new JTextField();
  public JTextField salaryField = new JTextField();

  public JComboBox<Role> roleComboBox = new JComboBox<>(Role.values());
  public JComboBox<Post> postComboBox = new JComboBox<>(Post.values());

  public JTable table = new JTable();
  public DefaultTableModel tableModel = new DefaultTableModel();

  public EmployeeView() {
    this.setLayout(new BorderLayout());
    this.add(inputsPanel, BorderLayout.NORTH);
    this.add(tablePanel, BorderLayout.CENTER);
    this.add(buttonsPanel, BorderLayout.SOUTH);

    tablePanel.setLayout(new BorderLayout());
    tablePanel.add(new JScrollPane(table));
    tableModel.setRowCount(0);
    tableModel.addColumn("ID");
    tableModel.addColumn("Last name");
    tableModel.addColumn("First name");
    tableModel.addColumn("Email");
    tableModel.addColumn("Phone");
    tableModel.addColumn("Salary");
    tableModel.addColumn("Post");
    tableModel.addColumn("Role");
    tableModel.addColumn("Solde");
    table.getTableHeader().setReorderingAllowed(false);
    table.setModel(tableModel);

    inputsPanel.setLayout(new GridLayout(7, 2));
    inputsPanel.add(new JLabel("Last name"));
    inputsPanel.add(lnameField);
    inputsPanel.add(new JLabel("First name"));
    inputsPanel.add(fnameField);
    inputsPanel.add(new JLabel("Email"));
    inputsPanel.add(emailField);
    inputsPanel.add(new JLabel("Phone"));
    inputsPanel.add(phoneField);
    inputsPanel.add(new JLabel("Salary"));
    inputsPanel.add(salaryField);
    inputsPanel.add(new JLabel("Role"));
    inputsPanel.add(roleComboBox);
    inputsPanel.add(new JLabel("Post"));
    inputsPanel.add(postComboBox);

    buttonsPanel.setLayout(new FlowLayout());
    buttonsPanel.add(addBtn);
    buttonsPanel.add(deleteBtn);
    buttonsPanel.add(updateBtn);
    buttonsPanel.add(showBtn);
    buttonsPanel.add(fillBtn);
    buttonsPanel.add(importBtn);
    buttonsPanel.add(exportBtn);
  }

  public void showSuccess(String message) {
    JOptionPane.showMessageDialog(null, message, null, JOptionPane.INFORMATION_MESSAGE);
  }

  public void showFailure(String message) {
    JOptionPane.showMessageDialog(null, message, null, JOptionPane.ERROR_MESSAGE);
  }
}
