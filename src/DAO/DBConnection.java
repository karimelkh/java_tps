package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
  private String url = "jdbc:postgresql://localhost:5432/java_db";
  private String dbuser = "postgres";
  private String dbpw = "pg1234";

  private static Connection con = null;

  public DBConnection() {
    if (con != null) return;
    try {
      Class.forName("org.postgresql.Driver");
      con = DriverManager.getConnection(url, dbuser, dbpw);
      System.out.println("Database Connection established!!");
    } catch (Exception e) {
      System.err.println(e);
    }
  }

  public Connection getConnection() {
    return con;
  }
}
