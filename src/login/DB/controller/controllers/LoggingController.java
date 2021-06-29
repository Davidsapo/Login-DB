package login.DB.controller.controllers;

import login.DB.model.Model;
import login.DB.model.models.LoginService;
import login.DB.model.models.User;
import login.DB.view.View;
import login.DB.view.views.LoginForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoggingController {

    private LoginService loginService;
    private LoginForm loginForm;

    public LoggingController(Model model, View view) {
        loginService = model.getLoginService();
        loginForm = view.getLoginForm();
        loginForm.addActionListener(new LoggingActionListener());
    }

    private class LoggingActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == loginForm.getLoginButton())
                loginButtonActionPerformed();
        }
    }

    private void loginButtonActionPerformed() {
        String login = loginForm.getLogin();
        char[] password = loginForm.getPassword();
        if (login.isEmpty() || password.length == 0) {
            loginForm.showMessage("Fields can not be empty!");
            return;
        }
        try {
            User user = loginService.login(login, password);
            loginForm.showMessage(user.toString());
        } catch (Exception throwables) {
            loginForm.showMessage(throwables.getMessage());
        }
    }
}

