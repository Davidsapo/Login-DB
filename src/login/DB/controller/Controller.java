package login.DB.controller;

import login.DB.controller.controllers.LoggingController;
import login.DB.controller.controllers.ProfileController;
import login.DB.controller.controllers.RegistrationController;
import login.DB.model.Model;
import login.DB.view.View;

public class Controller {

    public Controller(Model model, View view) {
        new LoggingController(model, view);
        new RegistrationController(model, view);
        new ProfileController(model,view);
    }
}
