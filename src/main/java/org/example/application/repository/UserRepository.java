package org.example.application.repository;

import org.example.application.models.User;
import org.example.server.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository implements IUserRepository {
    private Database database = new Database();
    private User result;
    private Connection connection;
    private PreparedStatement preparedStatement;

    @Override
    public User getUserWithName(User user) {
        try {
            connection = database.connect();
            String query = "SELECT * FROM person WHERE name = ?;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = new User(
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(6),
                        resultSet.getInt(7)
                );
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public User getUserWithToken(String token) {
        try {
            connection = database.connect();
            String query = "SELECT * FROM person WHERE token = ?;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, token);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = new User(
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(6),
                        resultSet.getInt(7)
                );

            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public void createUser(User user) {
        try {
            connection = database.connect();
            String query = "INSERT INTO person (name, password, token, money) VALUES (?, ?, ?, ?);";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getToken());
            preparedStatement.setInt(4, 20);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.closeConnection();
        }
        System.out.println("created new user");
    }

    @Override
    public boolean userExist(User user) {
        if (this.getUserWithName(user) == null) {
            return false;
        } else if (user.getUsername().equals(this.getUserWithName(user).getUsername()) || user.getPassword().equals(this.getUserWithName(user).getPassword())) {
            System.out.println("User exists already");
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean tokenExist(String token) {
        try {
            connection = database.connect();
            String query = "SELECT FROM person WHERE token = ?;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, token);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.closeConnection();
        }

    }

    @Override
    public void updateUser(User user) {
        try {
            connection = database.connect();
            String query = "UPDATE person SET Bio = ? Image = ? WHERE name = ?;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getBio());
            preparedStatement.setString(2, user.getImage());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public void buyWithCoin(int coin, User user) {
        System.out.println("Coin: " + coin);
        System.out.println("Name: " + user.getUsername());
        try {
            connection = database.connect();
            String query = "UPDATE person SET money = ? WHERE name = ?;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, coin);
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.closeConnection();
        }

    }

    public void closeConnection() {
        try {
            if(connection != null && !connection.isClosed()) {
                connection.close();
            }
            if(preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
