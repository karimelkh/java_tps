package Controller;

import Model.HolidayModel;
import View.HolidayView;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

// NOTE: Can I use another model in my controller
import Model.EmployeeModel;

public class HolidayController {
  private HolidayModel model = null;
  private HolidayView view = null;
  private int selectedRow = -1;

  public HolidayController(HolidayModel model, HolidayView view) {
    this.model = model;
    this.view = view;
    initTableEvent();
    populateFields();
    populateTable();
    initAddEvent();
    initDeleteEvent();
    initUpdateEvent();
    initRefreshEvent();
  }

  public void initAddEvent() {
    view.addBtn.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            int eid = view.getEid();
            String sd = view.getStartDate();
            String ed = view.getEndDate();
            String type = view.getType();
            model = new HolidayModel(eid, sd, ed, type);
            if (model.addHoliday()) {
              populateTable();
              emptyFields();
              view.showSuccess("Holiday added");
            } else {
              view.showFailure("Can't add holiday");
            }
          }
        });
  }

  public void initDeleteEvent() {
    view.deleteBtn.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            if (model.deleteHoliday((int) view.table.getValueAt(selectedRow, 0))) {
              populateTable();
              view.showSuccess("holiday of deleted");
            } else {
              view.showFailure("holiday can't be deleted");
            }
          }
        });
  }

  public void initUpdateEvent() {
    view.updateBtn.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            int id = (int) view.table.getValueAt(selectedRow, 0);
			HolidayModel newh = new HolidayModel(view.getEid(),view.getStartDate(),view.getEndDate(),view.getType());
            if (model.updateHoliday(id, newh)) {
				populateTable();
				emptyFields();
				view.showSuccess("holiday updated");
            } else {
              view.showFailure("holiday can't be updated");
            }
          }
        });
  }

  public void initRefreshEvent() {
    view.refreshBtn.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
					populateTable();
            populateFields();
          }
        });
  }

  public void initTableEvent() {
    view.table
        .getSelectionModel()
        .addListSelectionListener(
            new ListSelectionListener() {
              @Override
              public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                  selectedRow = view.table.getSelectedRow();
                  if (-1 < selectedRow) {}
                }
              }
            });
  }

  public void populateTable() {
    view.tableModel.setRowCount(0);
    for (HolidayModel hm : model.getAllHolidys()) {
      view.tableModel.addRow(
          new Object[] {
            hm.getId(),
            hm.getEid(),
            hm.getEmployeeName(),
            hm.getStartDate(),
            hm.getEndDate(),
            hm.getType()
          });
    }
  }

  public void populateFields() {
    ArrayList<EmployeeModel> employeesList = new EmployeeModel().getAllEmployees();
    ArrayList<String> list = new ArrayList<>();
    for (EmployeeModel elm : employeesList ) {
      list.add(elm.getId() + " - " + elm.getFname() + " " + elm.getLname());
    }
    view.employeeField.setModel(new DefaultComboBoxModel<String>(list.toArray(new String[0])));
  }

  public void emptyFields() {
    view.startDateField.setText("");
    view.endDateField.setText("");
    view.typeField.setSelectedIndex(0);
  }
}
