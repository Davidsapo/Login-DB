package login.DB.model;

import login.DB.model.models.LoginService;

public class Model {

    private LoginService loginService;

    public Model() {
        this.loginService = new LoginService();
    }

    public LoginService getLoginService() {
        return loginService;
    }
}
