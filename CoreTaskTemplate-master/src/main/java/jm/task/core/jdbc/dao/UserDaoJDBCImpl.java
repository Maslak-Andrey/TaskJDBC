package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    Util util = new Util();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Connection conn = util.connect();
        Statement statement = null;
        String sql = "CREATE TABLE users (" +
                "Id INT PRIMARY KEY AUTO_INCREMENT, " +
                "Name VARCHAR(20), " +
                "LastName VARCHAR(20), " +
                "Age INT);";
        try {
            statement = conn.createStatement();
            try {
                statement.executeUpdate(sql);
            } catch (SQLSyntaxErrorException ee){
                //continue
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                util.disconnect();
            }
        }
    }

    public void dropUsersTable() {
        Connection conn = util.connect();
        Statement statement = null;
        String sql = "DROP TABLE users;";
        try {
            statement = conn.createStatement();
            try {
                statement.executeUpdate(sql);
            } catch (SQLSyntaxErrorException ee){
                //continue
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                util.disconnect();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection conn = util.connect();
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO users(Name, LastName, Age) VALUES(?, ?, ?);";

        try {
            preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                util.disconnect();
            }
        }
    }

    public void removeUserById(long id) {
        Connection conn = util.connect();
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM users WHERE ID=?;";

        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                util.disconnect();
            }
        }
    }

    public List<User> getAllUsers() {
        Connection conn = util.connect();
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM users;";

        Statement statement = null;

        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("Id"));
                user.setName(resultSet.getString("Name"));
                user.setLastName(resultSet.getString("LastName"));
                user.setAge(resultSet.getByte("Age"));

                list.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                util.disconnect();
            }
        }
        return list;
    }

    public void cleanUsersTable() {
        Connection conn = util.connect();
        Statement statement = null;
        String sql = "TRUNCATE TABLE users;";
        try {
            statement = conn.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                util.disconnect();
            }
        }
    }
}
