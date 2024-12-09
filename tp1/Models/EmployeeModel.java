package Models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeModel {
  private String nom, prenom, email, tel;
  private double salaire;

  public enum Poste {
    INGENIEUR_ETUDE_ET_DEV,
    TEAM_LEADER,
    PILOTE
  };

  public enum Role {
    ADMIN,
    EMPLOYEE
  };

  public EmployeeModel(String nom, String prenom, String email, String tel, double salaire) {
    this.nom = nom;
    this.prenom = prenom;
    this.email = email;
    this.tel = tel;
    this.salaire = salaire;
  }

  public EmployeeModel(ResultSet rs) {
    try {
      this.nom = rs.getString("nom");
      this.prenom = rs.getString("prenom");
      this.email = rs.getString("email");
      this.tel = rs.getString("tel");
      this.salaire = rs.getDouble("salaire");
    } catch (SQLException e) {
      System.out.println(e);
    }
  }

  public String getNom() {
    return this.nom;
  }

  public String getPrenom() {
    return this.prenom;
  }

  public String getEmail() {
    return this.email;
  }

  public double getSalaire() {
    return this.salaire;
  }

  public String getTel() {
    return this.tel;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setSalaire(double salaire) {
    this.salaire = salaire;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  @Override
  public String toString() {
    return this.prenom
        + ", "
        + this.nom
        + ", "
        + this.email
        + ", "
        + this.tel
        + ", "
        + this.salaire;
  }
}
