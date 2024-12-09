package Views;

import DAO.EmployeeDAOImpl;
import Models.EmployeeModel;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EmployeeView extends JFrame {
  private JPanel pan = new JPanel();
  private JPanel tabPan = new JPanel();
  private JPanel panOne = new JPanel();
  private JPanel panTwo = new JPanel();

  public static JTextField nomField = new JTextField();
  public static JTextField prenomField = new JTextField();
  public static JTextField emailField = new JTextField();
  public static JTextField telField = new JTextField();
  public static JTextField salaireField = new JTextField();

  public static JButton addBtn = new JButton("Ajouter");
  public static JButton delBtn = new JButton("Supprimer");
  public static JButton showBtn = new JButton("Afficher");

  public static JTable tab = new JTable();
  public static DefaultTableModel tabModel = new DefaultTableModel();

  public EmployeeView() {
    this.setTitle("Gestion des employes");
    this.setSize(700, 240);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    this.add(pan, BorderLayout.NORTH);
    this.add(tabPan, BorderLayout.CENTER);

    pan.setLayout(new GridLayout(2, 1));
    pan.add(panOne);
    pan.add(panTwo);

    tabPan.add(new JScrollPane(tab));
    tabModel.setRowCount(0);
    tabModel.addColumn("Nom");
    tabModel.addColumn("Prenom");
    tabModel.addColumn("Age");
    tab.setModel(tabModel);

    panOne.setLayout(new GridLayout(3, 2));
    panOne.add(new JLabel("nom"));
    panOne.add(nomField);
    panOne.add(new JLabel("prenom"));
    panOne.add(prenomField);
    panOne.add(new JLabel("email"));
    panOne.add(emailField);
    panOne.add(new JLabel("tel"));
    panOne.add(telField);
    panOne.add(new JLabel("salaire"));
    panOne.add(salaireField);

    panTwo.setLayout(new FlowLayout(FlowLayout.CENTER));
    panTwo.add(addBtn);
    panTwo.add(delBtn);
    panTwo.add(showBtn);

    this.setVisible(true);
  }

  public static void afficherTable() {
    EmployeeModel p = null;
    String query = "SELECT * FROM employee";
    try {
      EmployeeDAOImpl.rs = EmployeeDAOImpl.st.executeQuery(query);
      EmployeeView.tabModel.setRowCount(0);
      while (EmployeeDAOImpl.rs.next()) {
        p = new EmployeeModel(EmployeeDAOImpl.rs);
        EmployeeView.tabModel.addRow(
            new Object[] {p.getNom(), p.getPrenom(), p.getEmail(), p.getTel(), p.getSalaire()});
        EmployeeView.tab.setModel(EmployeeView.tabModel);
        System.out.println(p);
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public static void emptyFields() {
    nomField.setText("");
    prenomField.setText("");
    emailField.setText("");
    telField.setText("");
    salaireField.setText("");
  }
}
