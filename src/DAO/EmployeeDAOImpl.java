package DAO;

import Model.EmployeeModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmployeeDAOImpl implements GenericDAOI<EmployeeModel> {
  private Connection con = null;

  public EmployeeDAOImpl() {
    con = new DBConnection().getConnection();
  }

  @Override
  public boolean add(EmployeeModel m) {
    boolean ret = false;
    PreparedStatement pst;
    String query =
        "INSERT INTO employee(lname, fname, email, phone, salary, post, role) VALUES (?, ?, ?, ?,"
            + " ?, ?, ?)";
    try {
      pst = con.prepareStatement(query);
      pst.setString(1, m.getLname());
      pst.setString(2, m.getFname());
      pst.setString(3, m.getEmail());
      pst.setString(4, m.getPhone());
      pst.setDouble(5, m.getSalary());
      pst.setString(6, m.getPost());
      pst.setString(7, m.getRole());
      if (1 <= pst.executeUpdate()) ret = true;
    } catch (SQLException e) {
      System.err.println(e);
    }
    return ret;
  }

  @Override
  public boolean delete(int id) {
    boolean ret = false;
    PreparedStatement pst;
    String query = "DELETE FROM employee WHERE id=?";
    try {
      pst = con.prepareStatement(query);
      pst.setInt(1, id);
      if (1 <= pst.executeUpdate()) ret = true;
    } catch (Exception e) {
      System.err.println(e);
    }
    return ret;
  }

  @Override
  public boolean update(int id, EmployeeModel m) {
    boolean ret = false;
    PreparedStatement pst;
    String query =
        "UPDATE employee SET lname=?, fname=?, email=?, phone=?, salary=?, post=?, role=? WHERE"
            + " id=?";

    try {
      pst = con.prepareStatement(query);
      pst.setString(1, m.getLname());
      pst.setString(2, m.getFname());
      pst.setString(3, m.getEmail());
      pst.setString(4, m.getPhone());
      pst.setDouble(5, m.getSalary());
      pst.setString(6, m.getPost());
      pst.setString(7, m.getRole());
      pst.setInt(8, id);
      if (1 <= pst.executeUpdate()) ret = true;
    } catch (Exception e) {
      System.err.println(e);
    }
    return ret;
  }

  @Override
  public ArrayList<EmployeeModel> getAll() {
    ArrayList<EmployeeModel> l = new ArrayList<>();
    Statement st;
    ResultSet rs = null;
    String query = "SELECT * FROM employee";

    try {
      st = con.createStatement();
      rs = st.executeQuery(query);
      while (rs.next()) {
        l.add(new EmployeeModel(rs));
      }
    } catch (SQLException e) {
      System.err.println(e);
    }

    return l;
  }

  @Override
  public EmployeeModel findById(int id) {
    PreparedStatement pst;
    String query = "SELECT * FROM employee WHERE id = ?";

    try {
      pst = con.prepareStatement(query);
      pst.setInt(1, id);
      return new EmployeeModel(pst.executeQuery(query));
    } catch (SQLException e) {
      System.err.println(e);
    }

    return null;
  }
}
