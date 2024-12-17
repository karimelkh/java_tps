package DAO;

import Models.HolidayModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HolidayDAOImpl implements GenericDAOI<HolidayModel> {
  private static Connection con = null;
  private static Statement st = null;

  public HolidayDAOImpl() {
    if (con != null && st != null) return;
    try {
      Class.forName("org.postgresql.Driver");
      con = DriverManager.getConnection(url, dbuser, dbpw);
      System.out.println("Connection established!!");
      st = con.createStatement();
    } catch (ClassNotFoundException | SQLException e) {
      System.err.println(e);
    }
  }

  @Override
  public List<HolidayModel> getAll() {
    List<HolidayModel> l = new ArrayList<HolidayModel>();
    ResultSet rs = null;
    String query = "SELECT * FROM holiday INNER JOIN employee ON holiday.eid = employee.id";

    try {
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
}
