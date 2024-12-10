import Controllers.EmployeeController;
import DAO.EmployeeDAOImpl;
import Views.EmployeeView;

public class Main {
  public static void main(String[] args) {
    EmployeeDAOImpl dao = new EmployeeDAOImpl();
    EmployeeController ec = new EmployeeController();
    EmployeeView ev = new EmployeeView();
  }
}
