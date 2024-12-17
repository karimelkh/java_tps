package DAO;

import Models.EmployeeModel;

interface EmployeeDAOI {
  public String url = "jdbc:postgresql://localhost:5432/java_db";
  public String dbuser = "postgres";
  public String dbpw = "pg1234";

  public boolean addEmployee(EmployeeModel em);

  public boolean deleteEmployee(int id);

  public boolean updateEmployee(int id, EmployeeModel em);
}
