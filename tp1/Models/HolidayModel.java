package Models;

import DAO.HolidayDAOImpl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class HolidayModel {
  private HolidayDAOImpl dao = null;

  private int eid, id;
  private String EmployeeName = "";
  private String startDate = null;
  private String endDate = null;
  private String type = null;

  // Constructors
  public HolidayModel() {
    this.dao = new HolidayDAOImpl();
  }

  public HolidayModel(HolidayDAOImpl dao) {
    this.dao = dao;
  }

  public HolidayModel(int eid, String startDate, String endDate, String type) {
    this.eid = eid;
    this.startDate = startDate;
    this.endDate = endDate;
    this.type = type;
    this.dao = new HolidayDAOImpl();
  }

  public HolidayModel(ResultSet rs) {
    if (dao == null) dao = new HolidayDAOImpl();
    try {
      this.id = rs.getInt("id");
      this.eid = rs.getInt("eid");
      this.EmployeeName = rs.getString("fname") + " " + rs.getString("lname");
      this.startDate = rs.getString("startdate");
      this.endDate = rs.getString("enddate");
      this.type = rs.getString("type");
    } catch (SQLException e) {
      System.out.println(e);
    }
  }

  // Getters
  public int getId() {
    return this.id;
  }

  public int getEid() {
    return this.eid;
  }

  public String getEmployeeName() {
    return this.EmployeeName;
  }

  public String getStartDate() {
    return this.startDate;
  }

  public String getEndDate() {
    return this.endDate;
  }

  public String getType() {
    return this.type;
  }

  // Setters
  public void setId(int id) {
    this.id = id;
  }

  public void setEid(int eid) {
    this.eid = eid;
  }

  public void setEmployeeName(String EmployeeName) {
    this.EmployeeName = EmployeeName;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public void setType(String type) {
    this.type = type;
  }

  // Methods
  public void addHoliday() {
    // check if still enough solde
    if (true) {
      if (dao.add(this)) {
        // decrement solde
        // notify the user
      }
    } else {
      // warn the user
      System.err.println("Can't add a holiday, say \"WHY?\"");
    }
  }

  public void deleteHoliday(int id) {
    if (dao.delete(id)) {
      // notify the user
    }
  }

  public void updateHoliday(int id, HolidayModel n) {
    if (dao.update(id, n)) {
      // success
    } else {
      // failure
    }
  }

  public List<HolidayModel> getAllHolidys() {
    return dao.getAll();
  }

  public HolidayModel findHolidayById(int id) {
    return this.dao.findById(id);
  }
}
