package Controllers;

import DAO.EmployeeDAOImpl;
import Models.Employee;
import Views.EmployeeView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class EmployeeController {

  public EmployeeController() {
    initAddEvent();
    initDeleteEvent();
    initUpdateEvent();
    initShowEvent();
  }

  private void initAddEvent() {
    EmployeeView.addBtn.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            Employee em =
                new Employee(
                    EmployeeView.lnameField.getText(),
                    EmployeeView.fnameField.getText(),
                    EmployeeView.emailField.getText(),
                    EmployeeView.phoneField.getText(),
                    Double.parseDouble(EmployeeView.salaryField.getText()),
                    EmployeeView.postComboBox.getSelectedItem().toString(),
                    EmployeeView.roleComboBox.getSelectedItem().toString());

            boolean isAdded = em.addEmployee();
            // EmployeeView.emptyFields();

            if (isAdded) {
              // em.setId(id);
              populateTable();
              JOptionPane.showMessageDialog(
                  null, "Employee " + em + " added!", "New", JOptionPane.INFORMATION_MESSAGE);

            } else {
              JOptionPane.showMessageDialog(
                  null, "Can't add employee " + em, "New", JOptionPane.ERROR_MESSAGE);
            }
          }
        });
  }

  private void initDeleteEvent() {
    EmployeeView.delBtn.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            int id = Integer.parseInt(EmployeeView.idField.getText());
            boolean isDeleted = Employee.deleteEmployee(id);
            // EmployeeView.emptyFields();
            if (isDeleted) {
              // populateTable();
              JOptionPane.showMessageDialog(
                  null,
                  "Employee id " + id + " deleted!",
                  "Delete",
                  JOptionPane.INFORMATION_MESSAGE);

            } else {
              JOptionPane.showMessageDialog(
                  null, "Can't delete Employee of id " + id, "Delete", JOptionPane.ERROR_MESSAGE);
            }
          }
        });
  }

  private void initUpdateEvent() {
    EmployeeView.updateBtn.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            int id = Integer.parseInt(EmployeeView.idField.getText());
            Employee em =
                new Employee(
                    EmployeeView.lnameField.getText(),
                    EmployeeView.fnameField.getText(),
                    EmployeeView.emailField.getText(),
                    EmployeeView.phoneField.getText(),
                    Double.parseDouble(EmployeeView.salaryField.getText()),
                    EmployeeView.postComboBox.getSelectedItem().toString(),
                    EmployeeView.roleComboBox.getSelectedItem().toString());

            boolean isUpdated = em.updateEmployee(id);
            // EmployeeView.emptyFields();
            if (isUpdated) {
              populateTable();
              JOptionPane.showMessageDialog(
                  null,
                  "Employee id " + id + " updated with values: " + em,
                  "Update",
                  JOptionPane.INFORMATION_MESSAGE);

            } else {
              JOptionPane.showMessageDialog(
                  null,
                  "Can't update Employee of id " + id + " with values: " + em,
                  "Update",
                  JOptionPane.ERROR_MESSAGE);
            }
          }
        });
  }

  private void initShowEvent() {
    EmployeeView.showBtn.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            populateTable();
          }
        });
  }

  public static void populateTable() {
    Employee em = null;
    String query = "SELECT * FROM employee";
    try {
      EmployeeDAOImpl.rs = EmployeeDAOImpl.st.executeQuery(query);
      EmployeeView.tabModel.setRowCount(0);
      while (EmployeeDAOImpl.rs.next()) {
        em = new Employee(EmployeeDAOImpl.rs);
        EmployeeView.tabModel.addRow(
            new Object[] {
              em.getId(),
              em.getLname(),
              em.getFname(),
              em.getEmail(),
              em.getPhone(),
              em.getSalary(),
              em.getPost(),
              em.getRole()
            });
        EmployeeView.tab.setModel(EmployeeView.tabModel);
        System.out.println(em);
      }
    } catch (Exception e) {
      System.err.println(e);
    }
  }

  public static void emptyFields() {
    EmployeeView.idField.setText("");
    EmployeeView.lnameField.setText("");
    EmployeeView.fnameField.setText("");
    EmployeeView.emailField.setText("");
    EmployeeView.phoneField.setText("");
    EmployeeView.salaryField.setText("");
    EmployeeView.postComboBox.setSelectedIndex(0);
    EmployeeView.roleComboBox.setSelectedIndex(0);
  }
}
