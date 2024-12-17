package DAO;

import java.util.List;

public interface GenericDAOI<T> {
  public String url = "jdbc:postgresql://localhost:5432/java_db";
  public String dbuser = "postgres";
  public String dbpw = "pg1234";

  public List<T> getAll();

  public T findById(int id);

  public boolean add(T m);

  public boolean delete(int id);

  public boolean update(int id, T m);
}
