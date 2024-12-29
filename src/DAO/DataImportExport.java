package DAO;

import java.io.IOException;
import java.util.List;

public interface DataImportExport<T> {
  void importData(String filename) throws IOException;

  void exportData(String filename, List<T> data) throws IOException;
}

