import Controllers.*;
import Models.*;
import Views.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Main {
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setTitle("Employees & Holidays Management");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800, 500);

    JTabbedPane tabbedPane = new JTabbedPane();
    frame.add(tabbedPane);

    JPanel eview = new EmployeeView();
    JPanel hview = new HolidayView();
    tabbedPane.addTab("Employees", eview);
    tabbedPane.addTab("Holidays", hview);

    // Employee part
    EmployeeModel em = new EmployeeModel();
    HolidayController ec = new HolidayController(em, (EmployeeView) eview);
    //
    // Holiday part
    HolidayModel hm = new HolidayModel();
    HolidayController hc = new HolidayController(hm, (HolidayView) hview);

    frame.setVisible(true);
  }
}
