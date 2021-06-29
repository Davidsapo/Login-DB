package login.DB;

import login.DB.controller.Controller;
import login.DB.model.Model;
import login.DB.view.View;

public class LoginDB {

    private Model model;
    private View view;
    private Controller controller;

    public LoginDB() {
        model = new Model();
        view = new View();
        controller = new Controller(model, view);
        view.getLoginForm().setVisible(true);
    }
}
