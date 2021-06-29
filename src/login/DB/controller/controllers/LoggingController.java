package login.DB.controller.controllers;

import login.DB.dao.DaoException;
import login.DB.model.Model;
import login.DB.model.models.User;
import login.DB.view.View;
import login.DB.view.views.LoginForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoggingController {

    private Model model;
    private View view;

    public LoggingController(Model model, View view) {
        this.model = model;
        this.view = view;
        view.getLoginForm().addActionListener(new LoggingActionListener());
    }

    private class LoggingActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == view.getLoginForm().getLoginButton())
                loginButtonActionPerformed();
            if (e.getSource() == view.getLoginForm().getRegisterButton()){
                view.getLoginForm().dispose();
                view.getRegistrationForm().setVisible(true);
            }
        }
    }

    private void loginButtonActionPerformed() {
        String login = view.getLoginForm().getLogin();
        char[] password = view.getLoginForm().getPassword();
        if (login.isEmpty() || password.length == 0) {
            view.getLoginForm().showMessage("Fields can not be empty!");
            return;
        }
        try {
            User user = model.getMySQLUserDao().loginUser(login, new String(password));
            view.getLoginForm().showMessage(user.toString());
        } catch (DaoException e) {
            view.getLoginForm().showMessage(e.getMessage());
        }

    }
}

