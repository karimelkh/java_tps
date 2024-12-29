package DAO;

import Model.EmployeeModel;
import Model.HolidayModel;
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

public class HolidayDAOImpl implements GenericDAOI<HolidayModel>, DataImportExport<HolidayModel> {
  private Connection con = null;
  private EmployeeDAOImpl edao;

  public HolidayDAOImpl() {
    con = new DBConnection().getConnection();
    edao = new EmployeeDAOImpl();
  }

  @Override
  public List<HolidayModel> getAll() {
    List<HolidayModel> l = new ArrayList<HolidayModel>();
    Statement st;
    ResultSet rs = null;
    String query = "SELECT * FROM holiday INNER JOIN employee ON holiday.eid = employee.id";

    try {
      st = con.createStatement();
      rs = st.executeQuery(query);
      while (rs.next()) {
        l.add(new HolidayModel(rs));
      }
    } catch (SQLException e) {
      System.err.println(e);
    }

    return l;
  }

  // static?
  @Override
  public HolidayModel findById(int id) {
    PreparedStatement pst;
    String query = "SELECT * FROM holiday WHERE eid = ?";

    try {
      pst = con.prepareStatement(query);
      return new HolidayModel(pst.executeQuery(query));
    } catch (SQLException e) {
      System.err.println(e);
    }

    return null;
  }

  @Override
  public boolean add(HolidayModel h) {
    PreparedStatement pst;
    String query =
        "INSERT INTO holiday(eid, startdate, enddate, type) VALUES (?, CAST(? AS DATE), "
            + "CAST(? AS DATE), ?)";
    try {
      pst = con.prepareStatement(query);
      pst.setInt(1, h.getEid());
      pst.setString(2, h.getStartDate());
      pst.setString(3, h.getEndDate());
      pst.setString(4, h.getType());
      if (1 <= pst.executeUpdate()) return true;
    } catch (SQLException e) {
      System.err.println(e);
    }

    return false;
  }

  @Override
  public boolean delete(int id) {
    PreparedStatement pst;
    String query = "DELETE FROM holiday WHERE id = ?";
    try {
      pst = con.prepareStatement(query);
      pst.setInt(1, id);
      if (1 <= pst.executeUpdate()) return true;
    } catch (SQLException e) {
      System.err.println(e);
    }

    return false;
  }

  @Override
  public boolean update(int id, HolidayModel h) {
    PreparedStatement pst;
    String query =
        "UPDATE holiday SET eid=?, startdate=CAST(? AS DATE), enddate=CAST(? AS DATE), type=?"
            + " WHERE id=?";
    try {
      pst = con.prepareStatement(query);
      pst.setInt(1, h.getEid());
      pst.setString(2, h.getStartDate());
      pst.setString(3, h.getEndDate());
      pst.setString(4, h.getType());
      pst.setInt(5, id);
      if (1 <= pst.executeUpdate()) return true;
    } catch (SQLException e) {
      System.err.println(e);
    }

    return false;
  }

  public int updateSolde(int id, int decriment) {
    PreparedStatement pst;
    ResultSet rs;
    int newSolde = -1;

    try {
      pst = con.prepareStatement("SELECT solde FROM employee WHERE id=?");
      pst.setInt(1, id);
      rs = pst.executeQuery();
      rs.next();
      newSolde = rs.getInt("solde") - decriment;
    } catch (SQLException e) {
      System.err.println(e);
    }

    try {
      pst = con.prepareStatement("UPDATE employee SET solde=? WHERE id=?");
      pst.setInt(1, newSolde);
      pst.setInt(2, id);
      pst.executeUpdate();
    } catch (SQLException e) {
      System.err.println(e);
    }
    return newSolde;
  }

  @Override
  public void exportData(String filename, List<HolidayModel> data) throws IOException {
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
      writer.write("First Name,Last Name,Email,Phone,Role,Poste,Salary");
      writer.newLine();
      for (HolidayModel elm : data) {
        EmployeeModel em = edao.findById(elm.getEid());
        String line =
            String.format(
                "%s,%s,%s,%s,%s,%s,%.2f",
                em.getFname(),
                em.getLname(),
                em.getEmail(),
                em.getPhone(),
                em.getRole(),
                em.getPost(),
                em.getSalary());
        writer.write(line);
        writer.newLine();
      }
      writer.close();
    } catch (Exception e) {
      System.err.println(e);
    }
  }

  // Not test/Not used
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
}
