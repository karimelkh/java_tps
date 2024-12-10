package Views;

import Controllers.EmployeeController;
import Models.Employee.Post;
import Models.Employee.Role;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EmployeeView extends JFrame {
  private JPanel pan = new JPanel();
  private JPanel tabPan = new JPanel();
  private JPanel panOne = new JPanel();
  private JPanel panTwo = new JPanel();

  public static JTextField idField = new JTextField();
  public static JTextField lnameField = new JTextField("lname");
  public static JTextField fnameField = new JTextField("fname");
  public static JTextField emailField = new JTextField("email");
  public static JTextField phoneField = new JTextField("+21200110055");
  public static JTextField salaryField = new JTextField("5000");

  public static JComboBox<Role> roleComboBox = new JComboBox<>(Role.values());
  public static JComboBox<Post> postComboBox = new JComboBox<>(Post.values());

  public static JButton addBtn = new JButton("Add");
  public static JButton delBtn = new JButton("Delete");
  public static JButton updateBtn = new JButton("Update");
  public static JButton showBtn = new JButton("Show");

  public static JTable tab = new JTable();
  public static DefaultTableModel tabModel = new DefaultTableModel();

  public EmployeeView() {
    this.setTitle("Employees management");
    this.setSize(800, 400);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    this.add(pan, BorderLayout.NORTH);
    this.add(tabPan, BorderLayout.CENTER);

    pan.setLayout(new GridLayout(2, 1));
    pan.add(panOne);
    pan.add(panTwo);

    tabPan.add(new JScrollPane(tab));
    tabModel.setRowCount(0);
    tabModel.addColumn("ID");
    tabModel.addColumn("Last name");
    tabModel.addColumn("First name");
    tabModel.addColumn("Email");
    tabModel.addColumn("Phone");
    tabModel.addColumn("Salary");
    tabModel.addColumn("Post");
    tabModel.addColumn("Role");
    tab.setModel(tabModel);

    panOne.setLayout(new GridLayout(8, 2));
    panOne.add(new JLabel("ID"));
    panOne.add(idField);
    panOne.add(new JLabel("Last name"));
    panOne.add(lnameField);
    panOne.add(new JLabel("First name"));
    panOne.add(fnameField);
    panOne.add(new JLabel("Email"));
    panOne.add(emailField);
    panOne.add(new JLabel("Phone"));
    panOne.add(phoneField);
    panOne.add(new JLabel("Salary"));
    panOne.add(salaryField);
    panOne.add(new JLabel("Role"));
    panOne.add(roleComboBox);
    panOne.add(new JLabel("Post"));
    panOne.add(postComboBox);

    panTwo.setLayout(new FlowLayout(FlowLayout.CENTER));
    panTwo.add(addBtn);
    panTwo.add(delBtn);
    panTwo.add(updateBtn);
    panTwo.add(showBtn);

    EmployeeController.populateTable();
    this.setVisible(true);
  }
}
