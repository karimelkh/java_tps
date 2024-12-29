package Model;

import DAO.HolidayDAOImpl;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class HolidayModel {
  private HolidayDAOImpl dao = null;

  private int eid, id;
  private String EmployeeName;
  private String startDate;
  private String endDate;
  private String type;

  private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  public enum HolidayType {
    Payed_holiday,
    Unpayed_holiday,
    Sickness_holiday
  };

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
  public boolean addHoliday() {
    LocalDate sd = LocalDate.parse(startDate, formatter);
    LocalDate ed = LocalDate.parse(endDate, formatter);
    int daysBetween = (int) ChronoUnit.DAYS.between(sd, ed);
    int oldSolde = dao.updateSolde(eid, 0);

    if (oldSolde > 0
        && daysBetween > 0
        && daysBetween <= oldSolde
        && ed.isAfter(sd)
        && sd.isAfter(LocalDate.now())) {
      if (dao.add(this)) {
        dao.updateSolde(eid, daysBetween);
        return true;
      }
    }
    return false;
  }

  public boolean deleteHoliday(int id) {
    return dao.delete(id);
  }

  public boolean updateHoliday(int id, HolidayModel n) {
    return dao.update(id, n);
  }

  public List<HolidayModel> getAllHolidys() {
    return dao.getAll();
  }

  public HolidayModel findHolidayById(int id) {
    return this.dao.findById(id);
  }

  @Override
  public String toString() {
    return "Holiday for Employee " + EmployeeName + " from " + startDate + " to " + endDate;
  }

  public void exportData(String filepath, List<HolidayModel> data) throws IOException {
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
