package Models;

import DAO.EmployeeDAOImpl;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Employee {
  private static EmployeeDAOImpl dao = null;
  private String lname, fname, email, phone, post, role;
  private double salary;
  private int id;

  public enum Post {
    STUDY_AND_DEV_ENGINEER,
    TEAM_LEADER,
    PILOTE
  };

  public enum Role {
    ADMIN,
    EMPLOYEE
  };

  public Employee(
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
  }

  public Employee(ResultSet rs) {
    dao = new EmployeeDAOImpl();
    try {
      this.id = rs.getInt("id");
      this.lname = rs.getString("lname");
      this.fname = rs.getString("fname");
      this.email = rs.getString("email");
      this.phone = rs.getString("phone");
      this.salary = rs.getDouble("salary");
      this.post = rs.getString("post");
      this.role = rs.getString("role");
    } catch (SQLException e) {
      System.out.println(e);
    }
  }

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
    // add some tests
    return dao.addEmployee(this);
  }

  public static boolean deleteEmployee(int id) {
    // add some tests
    return dao.deleteEmployee(id);
  }

  public boolean updateEmployee(int id) {
    // add some tests
    return dao.updateEmployee(id, this);
  }
}
