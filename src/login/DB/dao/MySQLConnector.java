package login.DB.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnector {

    private static final String URL = "jdbc:mysql://localhost:3306/login_db";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";
    private static boolean driverLoaded = false;

    public static Connection getConnection() throws SQLException {
        if (!driverLoaded) {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            driverLoaded = true;
        }
        return DriverManager.getConnection(URL, LOGIN, PASSWORD);
    }
}
