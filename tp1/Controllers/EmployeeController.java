package Controllers;

import DAO.EmployeeDAOImpl;
import Models.EmployeeModel;
import Views.EmployeeView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeController {
  public EmployeeController() {
    EmployeeView.addBtn.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            EmployeeDAOImpl.ajouter(
                new EmployeeModel(
                    EmployeeView.nomField.getText(),
                    EmployeeView.prenomField.getText(),
                    EmployeeView.emailField.getText(),
                    EmployeeView.telField.getText(),
                    Double.parseDouble(EmployeeView.salaireField.getText())));
          }
        });

    EmployeeView.delBtn.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            EmployeeDAOImpl.supprimer(
                EmployeeView.nomField.getText(),
                EmployeeView.prenomField.getText(),
                EmployeeView.emailField.getText(),
                EmployeeView.telField.getText(),
                Double.parseDouble(EmployeeView.salaireField.getText()));
          }
        });

    EmployeeView.showBtn.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            EmployeeView.afficherTable();
          }
        });
  }

  public static void afficherTable() {
    EmployeeModel p = null;
    String query = "SELECT * FROM employee";
    try {
      rs = st.executeQuery(query);
      tabModel.setRowCount(0);
      while (rs.next()) {
        p = new EmployeeModel(rs);
        tabModel.addRow(
            new Object[] {p.getNom(), p.getPrenom(), p.getEmail(), p.getTel(), p.getSalaire()});
        tab.setModel(tabModel);
        System.out.println(p);
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
