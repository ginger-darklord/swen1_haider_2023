package org.example.application.repository;

import org.example.application.models.User;
import org.example.server.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserRepository implements IUserRepository {
    private Database database = new Database();
    private User result;
    private Connection connection;
    private PreparedStatement preparedStatement;

    @Override
    public User getUserWithName(User user) {
        try {
            connection = database.connect();
            String query = "SELECT * FROM person WHERE username = ?;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = new User(
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(6),
                        resultSet.getInt(7),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(8),
                        resultSet.getInt(9)
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
                        resultSet.getInt(7),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(8),
                        resultSet.getInt(9)
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
            String query = "INSERT INTO person (username, password, token, money, elo) VALUES (?, ?, ?, ?, ?);";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getToken());
            preparedStatement.setInt(4, 20);
            preparedStatement.setInt(5, 100);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.closeConnection();
        }
        System.out.println("created new user");
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
    public void updateUser(User user, String username) {
        try {
            connection = database.connect();
            String query = "UPDATE person SET name = ?, bio = ?, image = ? WHERE username = ?;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getBio());
            preparedStatement.setString(3, user.getImage());
            preparedStatement.setString(4, username);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public void buyWithCoin(int coin, User user) {
        try {
            connection = database.connect();
            String query = "UPDATE person SET money = ? WHERE username = ?;";

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

    @Override
    public ArrayList<Integer> getScoreboard() {
        try {
            connection = database.connect();
            String query = "SELECT elo FROM person;";
            ArrayList<Integer> eloValues = new ArrayList<>();

            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                eloValues.add(resultSet.getInt(9));
            }
            return eloValues;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public void battleReady(User user, boolean ready) {
        try {
            connection = database.connect();
            String query = "INSERT INTO battle(username, ready) VALUES (?,?);";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setBoolean(2, ready);
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
