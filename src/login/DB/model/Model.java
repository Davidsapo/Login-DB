package login.DB.model;

public class Model {

    private LoginService loginService;

    public Model() {
        this.loginService = new LoginService();
    }

    public LoginService getLoginService() {
        return loginService;
    }
}
