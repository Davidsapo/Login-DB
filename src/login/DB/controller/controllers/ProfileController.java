package login.DB.controller.controllers;

import login.DB.dao.DaoException;
import login.DB.model.Model;
import login.DB.model.models.ProfileManager;
import login.DB.view.View;
import login.DB.view.views.ProfileForm;

import javax.naming.InvalidNameException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileController {

    private ProfileManager profileManager;
    private ProfileForm profileForm;
    private View view;

    public ProfileController(Model model, View view) {
        profileManager = model.getProfileManager();
        this.view = view;
        profileForm = view.getProfileForm();
        profileForm.addActionListener(new ProfileFormActionListener());
    }

    private class ProfileFormActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == profileForm.getBackButton()) {
                profileForm.dispose();
                profileForm.reset();
                view.getLoginForm().setVisible(true);
            } else if (e.getSource() == profileForm.getDeleteButton()) {
                deleteButtonActionPerformed();
            } else if (e.getSource() == profileForm.getUpdateButton()) {
                updateButtonActionPerformed();
            }
        }
    }

    private void updateButtonActionPerformed() {
        String name = profileForm.getName();
        String surname = profileForm.getSurname();
        int age = profileForm.getAge();
        String city = profileForm.getCity();
        String email = profileForm.getEmail();
        String Username = profileForm.getUsername();
        String password = profileForm.getPassword();

        if (name.isEmpty() || surname.isEmpty() || city.isEmpty() || Username.isEmpty() || password.isEmpty()){
            profileForm.showMessage("Fields can not be empty.");
            return;
        }

        try {
            profileManager.updateUser(name,surname,age,city,email,Username,password);
            profileForm.showMessage("User updated.");
        } catch (InvalidNameException | DaoException e) {
            profileForm.showMessage(e.getMessage());
        }
    }

    private void deleteButtonActionPerformed() {
        try {
            profileManager.deleteUser();
        } catch (DaoException e) {
            profileForm.showMessage(e.getMessage());
        }
        profileForm.showMessage("User deleted.");
        profileForm.dispose();
        view.getLoginForm().setVisible(true);
    }
}
