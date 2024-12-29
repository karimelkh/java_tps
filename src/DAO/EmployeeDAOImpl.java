package DAO;

import Model.EmployeeModel;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl
    implements GenericDAOI<EmployeeModel>, DataImportExport<EmployeeModel> {
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
    Statement st;
    ResultSet rs;
    String query = "SELECT * FROM employee WHERE id=" + id;

    try {
      st = con.createStatement();
      rs = st.executeQuery(query);
      if (rs.next()) return new EmployeeModel(rs);
      return null;
    } catch (SQLException e) {
      System.err.println(e);
    }

    return null;
  }

  @Override
  public void importData(String filename) throws IOException {
    PreparedStatement pst;
    String query =
        "INSERT INTO employee(fname, lname, email, phone, role, post, salary) VALUES (?, ?, ?, ?,"
            + " ?, ?, ?)";
    try {
      BufferedReader reader = new BufferedReader(new FileReader(filename));
      pst = con.prepareStatement(query);
      String line = reader.readLine();
      while ((line = reader.readLine()) != null) {
        String[] data = line.split(",");
        if (data.length == 7) {
          pst.setString(1, data[0].trim());
          pst.setString(2, data[1].trim());
          pst.setString(3, data[2].trim());
          pst.setString(4, data[3].trim());
          pst.setString(5, data[4].trim());
          pst.setString(6, data[5].trim());
          pst.setDouble(7, Double.parseDouble(data[6].trim()));
          pst.addBatch();
        }
      }
      pst.executeBatch();
      reader.close();
      System.out.println("Employees imported successfully!");
    } catch (IOException | SQLException e) {
      System.err.println(e);
    }
  }

  @Override
  public void exportData(String filename, List<EmployeeModel> data) throws IOException {
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
      writer.write("First Name,Last Name,Email,Phone,Role,Poste,Salary");
      writer.newLine();
      for (EmployeeModel elm : data) {
        String line =
            String.format(
                "%s,%s,%s,%s,%s,%s,%.2f",
                elm.getFname(),
                elm.getLname(),
                elm.getEmail(),
                elm.getPhone(),
                elm.getRole(),
                elm.getPost(),
                elm.getSalary());
        writer.write(line);
        writer.newLine();
      }
      writer.close();
    } catch (Exception e) {
      System.err.println(e);
    }
  }
}
