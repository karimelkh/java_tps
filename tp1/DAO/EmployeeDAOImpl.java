package DAO;

import Models.EmployeeModel;
import Views.EmployeeView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;

public class EmployeeDAOImpl implements EmployeeDAOI {
  public Connection con = null;
  public static Statement st = null;
  public static ResultSet rs = null;

  public EmployeeDAOImpl() {
    try {
      Class.forName("org.postgresql.Driver");
      this.con = DriverManager.getConnection(url, dbuser, dbpw);
      System.out.println("Connection established!!");
      st = this.con.createStatement();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public static void ajouter(EmployeeModel p) {
    String query =
        "INSERT INTO employee(nom, prenom, email, tel, salaire) VALUES ( '"
            + p.getNom()
            + "', '"
            + p.getPrenom()
            + "', '"
            + p.getEmail()
            + "', '"
            + p.getTel()
            + "', "
            + p.getSalaire()
            + ")";
    try {
      System.out.println(query);
      System.out.println("insert query returned " + st.executeUpdate(query));
      EmployeeView.emptyFields();
      EmployeeView.afficherTable();
      JOptionPane.showMessageDialog(null, p.toString(), "AJOUTER", JOptionPane.INFORMATION_MESSAGE);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public static void supprimer(
      String nom, String prenom, String email, String tel, double salaire) {
    String query =
        "DELETE FROM employee WHERE nom = '"
            + nom
            + "' AND prenom='"
            + prenom
            + "' AND email='"
            + email
            + "' AND tel='"
            + tel
            + "' AND salaire='"
            + salaire
            + "';";
    try {
      System.out.println("delete query returned " + st.executeUpdate(query));
      EmployeeView.emptyFields();
      EmployeeView.afficherTable();
      JOptionPane.showMessageDialog(
          null,
          (new EmployeeModel(nom, prenom, email, tel, salaire).toString()),
          "SUPPRIMER",
          JOptionPane.INFORMATION_MESSAGE);
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
