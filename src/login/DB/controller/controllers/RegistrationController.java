package login.DB.controller.controllers;

import login.DB.dao.DaoException;
import login.DB.model.Model;
import login.DB.view.View;
import login.DB.view.views.RegistrationForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationController {

    private Model model;
    private View view;
    private RegistrationForm registrationForm;

    public RegistrationController(Model model, View view) {
        this.model = model;
        this.view = view;
        registrationForm = view.getRegistrationForm();
        registrationForm.addActionListeners(new RegistrationFormActionListener());
    }

    private class RegistrationFormActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == registrationForm.getBackButton()){
                registrationForm.dispose();
                view.getLoginForm().setVisible(true);
            }
            else if(e.getSource() == registrationForm.getRegisterButton())
                registerButtonActionPerformed();
        }
    }

    private void registerButtonActionPerformed() {
        String name = registrationForm.getName();
        String surname = registrationForm.getSurname();
        int age = registrationForm.getAge();
        String city = registrationForm.getCity();
        String username = registrationForm.getUsername();
        String password = registrationForm.getPassword();

        if(name.isEmpty() || surname.isEmpty() || city.isEmpty() || username.isEmpty() || password.isEmpty()) {
            registrationForm.showMessage("Fields can not be empty");
            return;
        }

        try {
            model.getMySQLUserDao().registerUser(name,surname,age,city,username,password);
            registrationForm.showMessage("Registration successful.");
            registrationForm.dispose();
            view.getLoginForm().setVisible(true);
        } catch (DaoException e) {
            registrationForm.showMessage(e.getMessage());
        }
    }
}
