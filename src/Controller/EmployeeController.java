package Controller;

import Model.EmployeeModel;
import View.EmployeeView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class EmployeeController {

  private EmployeeView view;
  private EmployeeModel model;
  private int selectedRow = -1;

  public EmployeeController(EmployeeModel model, EmployeeView view) {
    this.model = model;
    this.view = view;
    populateTable();
    initAddEvent();
    initDeleteEvent();
    initUpdateEvent();
    initShowEvent();
    initFillEvent();
    initTableEvents();
  }

  private void initAddEvent() {
    view.addBtn.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            model =
                new EmployeeModel(
                    view.lnameField.getText(),
                    view.fnameField.getText(),
                    view.emailField.getText(),
                    view.phoneField.getText(),
                    Double.parseDouble(view.salaryField.getText()),
                    view.postComboBox.getSelectedItem().toString(),
                    view.roleComboBox.getSelectedItem().toString());

            boolean isAdded = model.addEmployee();

            if (isAdded) {
              emptyFields();
              populateTable();
              view.showSuccess("EmployeeModel " + model + " added!");
            } else {
              view.showFailure("Can't add employee " + model);
            }
          }
        });
  }

  private void initDeleteEvent() {
    view.deleteBtn.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            int id = (int) view.table.getValueAt(selectedRow, 0);
            boolean isDeleted = model.deleteEmployee(id);

            if (isDeleted) {
              populateTable();
              view.showSuccess("EmployeeModel id " + id + " deleted!");
            } else {
              view.showFailure("Can't delete EmployeeModel of id " + id);
            }
          }
        });
  }

  private void initUpdateEvent() {
    view.updateBtn.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            int id = (int) view.table.getValueAt(selectedRow, 0);
            EmployeeModel em =
                new EmployeeModel(
                    view.lnameField.getText(),
                    view.fnameField.getText(),
                    view.emailField.getText(),
                    view.phoneField.getText(),
                    Double.parseDouble(view.salaryField.getText()),
                    view.postComboBox.getSelectedItem().toString(),
                    view.roleComboBox.getSelectedItem().toString());

            boolean isUpdated = em.updateEmployee(id);
            if (isUpdated) {
              emptyFields();
              populateTable();
              view.showSuccess("EmployeeModel id " + id + " updated with values: " + em);
            } else {
              view.showFailure("Can't update EmployeeModel of id " + id + " with values: " + em);
            }
          }
        });
  }

  public void initShowEvent() {
    view.showBtn.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            populateTable();
          }
        });
  }

  public void initFillEvent() {
    view.fillBtn.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            fillFields();
          }
        });
  }

  public void initTableEvents() {
    view.table
        .getSelectionModel()
        .addListSelectionListener(
            new ListSelectionListener() {
              @Override
              public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                  selectedRow = view.table.getSelectedRow();
                }
              }
            });
  }

  public void populateTable() {
    view.tableModel.setRowCount(0);
    for (EmployeeModel elm : model.getAllEmployees()) {
      view.tableModel.addRow(
          new Object[] {
            elm.getId(),
            elm.getLname(),
            elm.getFname(),
            elm.getEmail(),
            elm.getPhone(),
            elm.getSalary(),
            elm.getPost(),
            elm.getRole(),
            elm.getSolde()
          });
    }
  }

  public void fillFields() {
    view.lnameField.setText((String) view.table.getValueAt(selectedRow, 1));
    view.fnameField.setText((String) view.table.getValueAt(selectedRow, 2));
    view.emailField.setText((String) view.table.getValueAt(selectedRow, 3));
    view.phoneField.setText((String) view.table.getValueAt(selectedRow, 4));
    view.salaryField.setText(view.table.getValueAt(selectedRow, 5).toString());
  }

  public void emptyFields() {
    view.lnameField.setText("");
    view.fnameField.setText("");
    view.emailField.setText("");
    view.phoneField.setText("");
    view.salaryField.setText("");
    view.postComboBox.setSelectedIndex(0);
    view.roleComboBox.setSelectedIndex(0);
  }
}
