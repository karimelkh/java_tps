package Views;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EmployeeView extends JFrame {
  // public Statement st = null;
  // public ResultSet rs = null;
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

  private JTable tab = new JTable();
  private DefaultTableModel tabModel = new DefaultTableModel();

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

  public static void emptyFields() {
    nomField.setText("");
    prenomField.setText("");
    emailField.setText("");
    telField.setText("");
    salaireField.setText("");
  }
}
