package Controllers;

import Models.HolidayModel;
import Views.HolidayView;
import java.awt.event.*;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class HolidayController {
  private HolidayModel model = null;
  private HolidayView view = null;
  private int selectedRow = -1;

  public HolidayController(HolidayModel model, HolidayView view) {
    this.model = model;
    this.view = view;
    initAddEvent();
    initDeleteEvent();
    initUpdateEvent();
    populateTable();
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
            model.addHoliday();
            populateTable();
            emptyFields();
          }
        });
  }

  public void initDeleteEvent() {
    view.deleteBtn.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            model.deleteHoliday((int) view.table.getValueAt(selectedRow, 0));
            populateTable();
          }
        });
  }

  public void initUpdateEvent() {
    view.updateBtn.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            int id = (int) view.table.getValueAt(selectedRow, 0);
            model.updateHoliday(
                id,
                new HolidayModel(
                    view.getEid(), view.getStartDate(), view.getEndDate(), view.getType()));
            populateTable();
            emptyFields();
          }
        });
  }

  public void populateTable() {
    List<HolidayModel> list = model.getAllHolidys();
    view.tableModel.setRowCount(0);
    for (HolidayModel hm : list) {
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

  public void emptyFields() {
    view.employeeField.setText("");
    view.startDateField.setText("");
    view.endDateField.setText("");
    view.typeField.setSelectedIndex(0);
  }
}
