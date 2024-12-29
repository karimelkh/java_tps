package Controller;

import Model.EmployeeModel;
import View.EmployeeView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

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
    initImportBtn();
    initExportBtn();
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
              view.showFailure("Can't add employee");
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

  private void initImportBtn() {
    view.importBtn.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            handleImport();
            populateTable();
          }
        });
  }

  private void initExportBtn() {
    view.exportBtn.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            handleExport();
          }
        });
  }

  private void handleImport() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileFilter(new FileNameExtensionFilter("csv file", "csv"));
    if (fileChooser.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {
      try {
        String filepath = fileChooser.getSelectedFile().getAbsolutePath();
        model.importData(filepath);
        view.showSuccess("Importing succeeds!");
      } catch (IOException e) {
        System.out.println(e);
        view.showFailure("Error while importing data: " + e.getMessage());
      }
    }
  }

  private void handleExport() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileFilter(new FileNameExtensionFilter("csv file", "csv"));
    if (fileChooser.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {
      try {
        String filepath = fileChooser.getSelectedFile().getAbsolutePath();
        if (!filepath.toLowerCase().endsWith(".csv")) {
          filepath += ".csv";
        }
        model.exportData(filepath, model.getAllEmployees());
        view.showSuccess("Exporting succeeds!");
      } catch (IOException e) {
        System.out.println(e);
        view.showFailure("Error while exporting data: " + e.getMessage());
      }
    }
  }
}
