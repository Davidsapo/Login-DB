package login.DB.view;

import login.DB.view.views.LoginForm;
import login.DB.view.views.ProfileForm;
import login.DB.view.views.RegistrationForm;

public class View {

    private LoginForm loginForm;
    private RegistrationForm registrationForm;
    private ProfileForm profileForm;

    public View() {
        this.loginForm = new LoginForm();
        registrationForm = new RegistrationForm();
        profileForm = new ProfileForm();
    }

    public LoginForm getLoginForm() {
        return loginForm;
    }

    public RegistrationForm getRegistrationForm() {
        return registrationForm;
    }

    public ProfileForm getProfileForm() {
        return profileForm;
    }
}
