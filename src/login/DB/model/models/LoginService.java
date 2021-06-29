package login.DB.model.models;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class LoginService {

    private static final String SQL_QUERY = "select * from users where login = ? and password = ?;";

    public User login(String login, char[] encodedPassword) throws SQLException {
        Connection connection = null;
        try {
            String password = new String(encodedPassword);
            connection = MySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getString("name"), resultSet.getString("surname"), resultSet.getString("email"), resultSet.getString("login"), resultSet.getString("password"));
            }
            throw new NoSuchElementException("Invalid login or password");
        } finally {
            if (connection != null)
                connection.close();
        }
    }
}
