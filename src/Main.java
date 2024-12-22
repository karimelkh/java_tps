import Controller.*;
import Model.*;
import View.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Main {
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setTitle("Employees & Holidays Management");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800, 500);

    // Employee
    JPanel eview = new EmployeeView();
    EmployeeModel em = new EmployeeModel();
    EmployeeController ec = new EmployeeController(em, (EmployeeView) eview);

    // Holiday
    JPanel hview = new HolidayView();
    HolidayModel hm = new HolidayModel();
    HolidayController hc = new HolidayController(hm, (HolidayView) hview);

    JTabbedPane tabbedPane = new JTabbedPane();
    frame.add(tabbedPane);

    tabbedPane.addTab("Employees", eview);
    tabbedPane.addTab("Holidays", hview);

    frame.setVisible(true);
  }
}
