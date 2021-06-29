package login.DB.view;

import login.DB.view.views.LoginForm;
import login.DB.view.views.RegistrationForm;

public class View {

    private LoginForm loginForm;
    private RegistrationForm registrationForm;

    public View() {
        this.loginForm = new LoginForm();
        registrationForm = new RegistrationForm();
    }

    public LoginForm getLoginForm() {
        return loginForm;
    }

    public RegistrationForm getRegistrationForm() {
        return registrationForm;
    }
}
