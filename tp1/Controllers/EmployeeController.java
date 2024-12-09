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
}
