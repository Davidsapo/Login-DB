package login.DB.model;

import login.DB.dao.MySQLUserDao;
import login.DB.model.models.ProfileManager;

public class Model {

    private MySQLUserDao mySQLUserDao;
    private ProfileManager profileManager;

    public Model() {
        mySQLUserDao = MySQLUserDao.getInstance();
        profileManager = new ProfileManager(mySQLUserDao);
    }

    public MySQLUserDao getMySQLUserDao() {
        return mySQLUserDao;
    }

    public ProfileManager getProfileManager() {
        return profileManager;
    }
}
