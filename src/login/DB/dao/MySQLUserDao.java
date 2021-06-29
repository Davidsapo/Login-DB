package login.DB.dao;

import login.DB.model.models.User;

import java.sql.*;

public class MySQLUserDao {

    private static MySQLUserDao mySQLUserDao = null;

    private static final String REGISTER_SQL_SCRIPT = "insert into users (name, surname, age, city, username, password) values (?,?,?,?,?,?);";
    private static final String LOGIN_SQL_SCRIPT = "select * from users where username = ? and password = ?;";
    private static final String EDIT_SQL_SCRIPT = "update users set name = ?, surname = ?, age = ?, city = ?, email = ?, username = ?, password = ? where id = ?;";
    private static final String DELETE_SQL_SCRIPT = "delete from users where id = ?;";

    private MySQLUserDao() {
    }

    public static MySQLUserDao getInstance() {
        if (mySQLUserDao == null)
            mySQLUserDao = new MySQLUserDao();
        return mySQLUserDao;
    }

    public void registerUser(String name, String surname, int age, String city, String username, String password) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = MySQLConnector.getConnection();
            statement = connection.prepareStatement(REGISTER_SQL_SCRIPT);
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setInt(3, age);
            statement.setString(4, city);
            statement.setString(5, username);
            statement.setString(6, password);
            statement.execute();

        } catch (SQLIntegrityConstraintViolationException e) {
            throw new DaoException("Such user already exists.");
        } catch (SQLException throwables) {
            throw new DaoException("Dao exception");
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException throwables) {
                throw new DaoException("Problems with database connection.");
            }
        }
    }

    public User loginUser(String username, String password) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = MySQLConnector.getConnection();
            statement = connection.prepareStatement(LOGIN_SQL_SCRIPT);
            statement.setString(1, username);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("surname"), resultSet.getInt("age"),
                        resultSet.getString("city"), resultSet.getString("username"),
                        resultSet.getString("password"));
                String email = resultSet.getString("email");
                if (email != null)
                    user.setEmail(email);
                return user;
            }
            throw new DaoException("Invalid login or password.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DaoException("Problems with database connection.");
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException throwables) {
                throw new DaoException("Problems with database connection.");
            }
        }
    }

    public void updateUser(User user) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = MySQLConnector.getConnection();
            statement = connection.prepareStatement(EDIT_SQL_SCRIPT);
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setInt(3, user.getAge());
            statement.setString(4, user.getCity());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getUsername());
            statement.setString(7, user.getPassword());
            statement.setInt(8, user.id);
            statement.execute();

        } catch (SQLIntegrityConstraintViolationException e) {
            throw new DaoException("Unsupported changes");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DaoException("Exception when updating.");
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException throwables) {
                throw new DaoException("Problems with database connection.");
            }
        }
    }

    public void deleteUser(User user) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = MySQLConnector.getConnection();
            statement = connection.prepareStatement(DELETE_SQL_SCRIPT);
            statement.setInt(1, user.id);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DaoException("Exception occurs when deleting user " + user.getUsername());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException throwables) {
                throw new DaoException("Problems with database connection.");
            }
        }
    }
}
