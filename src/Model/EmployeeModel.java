package Model;

import DAO.EmployeeDAOImpl;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {
  private EmployeeDAOImpl dao = null;
  private String lname, fname, email, phone, post, role;
  private double salary;
  private int id, solde;

  public enum Post {
    STUDY_AND_DEV_ENGINEER,
    TEAM_LEADER,
    PILOTE
  };

  public enum Role {
    ADMIN,
    EMPLOYEE
  };

  public EmployeeModel() {
    this.dao = new EmployeeDAOImpl();
  }

  public EmployeeModel(
      String lname,
      String fname,
      String email,
      String phone,
      double salary,
      String post,
      String role) {
    dao = new EmployeeDAOImpl();
    this.lname = lname;
    this.fname = fname;
    this.email = email;
    this.phone = phone;
    this.salary = salary;
    this.post = post;
    this.role = role;
    this.dao = new EmployeeDAOImpl();
  }

  public EmployeeModel(ResultSet rs) {
    this.dao = new EmployeeDAOImpl();
    try {
      this.id = rs.getInt("id");
      this.lname = rs.getString("lname");
      this.fname = rs.getString("fname");
      this.email = rs.getString("email");
      this.phone = rs.getString("phone");
      this.salary = rs.getDouble("salary");
      this.post = rs.getString("post");
      this.role = rs.getString("role");
      this.solde = rs.getInt("solde");
    } catch (SQLException e) {
      System.out.println(e);
    }
  }

  // Getters
  public int getId() {
    return this.id;
  }

  public String getLname() {
    return this.lname;
  }

  public String getFname() {
    return this.fname;
  }

  public String getEmail() {
    return this.email;
  }

  public double getSalary() {
    return this.salary;
  }

  public String getPhone() {
    return this.phone;
  }

  public String getPost() {
    return this.post;
  }

  public String getRole() {
    return this.role;
  }

  public int getSolde() {
    return this.solde;
  }

  // Setters
  public void setId(int id) {
    this.id = id;
  }

  public void setLname(String lname) {
    this.lname = lname;
  }

  public void setFname(String fname) {
    this.fname = fname;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void setPost(String post) {
    this.post = post;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public void setSolde(int solde) {
    this.solde = solde;
  }

  @Override
  public String toString() {
    return this.fname
        + ", "
        + this.lname
        + ", "
        + this.email
        + ", "
        + this.phone
        + ", "
        + this.post
        + ", "
        + this.role
        + ", "
        + this.salary;
  }

  public boolean addEmployee() {
    try {
      dao.add(this);
      return true;
    } catch (Exception e) {
      System.out.println(e);
      return false;
    }
  }

  public boolean deleteEmployee(int id) {
    return dao.delete(id);
  }

  public boolean updateEmployee(int id) {
    return dao.update(id, this);
  }

  public ArrayList<EmployeeModel> getAllEmployees() {
    return dao.getAll();
  }

  public void importData(String filepath) throws IOException {
    try {
      checkIfFileExists(new File(filepath));
      checksIsFile(new File(filepath));
      checksIsReadable(new File(filepath));
    } catch (IllegalArgumentException e) {
      System.out.println(e);
    }

    try {
      dao.importData(filepath);
    } catch (IOException e) {
      throw new IOException();
    }
  }

  public void exportData(String filepath, List<EmployeeModel> data) throws IOException {
    try {
      checkIfFileExists(new File(filepath));
      checksIsFile(new File(filepath));
      checksIsReadable(new File(filepath));
    } catch (IllegalArgumentException e) {
      System.out.println(e);
    }

    try {
      dao.exportData(filepath, data);
    } catch (IOException e) {
      throw new IOException();
    }
  }

  private boolean checkIfFileExists(File file) throws IllegalArgumentException {
    if (!file.exists()) {
      throw new IllegalArgumentException(file.getName() + ": No such file.");
    }
    return true;
  }

  private boolean checksIsFile(File file) throws IllegalArgumentException {
    if (!file.isFile()) {
      throw new IllegalArgumentException(file.getName() + ": is not a file.");
    }
    return true;
  }

  private boolean checksIsReadable(File file) throws IllegalArgumentException {
    if (!file.canRead()) {
      throw new IllegalArgumentException(file.getName() + ": can not read file.");
    }
    return true;
  }
}
