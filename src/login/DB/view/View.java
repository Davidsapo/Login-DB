package login.DB.view;

import login.DB.view.views.LoginForm;

public class View {

    private LoginForm loginForm;

    public View() {
        this.loginForm = new LoginForm();
    }

    public LoginForm getLoginForm() {
        return loginForm;
    }
}
