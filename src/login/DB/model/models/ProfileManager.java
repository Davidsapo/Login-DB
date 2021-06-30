package login.DB.model.models;

import login.DB.dao.DaoException;
import login.DB.dao.MySQLUserDao;

import javax.naming.InvalidNameException;

public class ProfileManager {

    private static final String EMAIL_PATTERN = "\\w+@[a-zA-Z]+.com";

    private MySQLUserDao mySQLUserDao;
    private User loggedUser;

    public ProfileManager(MySQLUserDao mySQLUserDao) {
        this.mySQLUserDao = mySQLUserDao;
    }

    public void updateUser(String name, String surname, int age, String city, String email, String username, String password) throws InvalidNameException, DaoException {
        if (email.isEmpty())
            email = null;
        else if (!email.matches(EMAIL_PATTERN))
            throw new InvalidNameException("Wrong email address.");

        User updatedUser = new User(loggedUser.id, name, surname, age, city, email, username, password);
        mySQLUserDao.updateUser(updatedUser);
        loggedUser = updatedUser;
    }

    public void deleteUser() throws DaoException {
        mySQLUserDao.deleteUser(loggedUser);
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }
}
