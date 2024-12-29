package DAO;

import java.util.List;

public interface GenericDAOI<T> {
  public List<T> getAll();

  public T findById(int id);

  public boolean add(T m);

  public boolean delete(int id);

  public boolean update(int id, T m);
}
