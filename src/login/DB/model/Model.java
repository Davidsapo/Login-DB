package login.DB.model;

import login.DB.dao.MySQLUserDao;

public class Model {

    private MySQLUserDao mySQLUserDao;

    public Model() {
        mySQLUserDao = MySQLUserDao.getInstance();
    }

    public MySQLUserDao getMySQLUserDao() {
        return mySQLUserDao;
    }
}
