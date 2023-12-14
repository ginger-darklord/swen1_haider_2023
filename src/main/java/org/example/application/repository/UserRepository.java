package org.example.application.repository;

import org.example.application.models.User;
import org.example.server.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository implements IUserRepository {
    //sql with prepared statements
    private Database database = new Database();
    private User newUser;

    @Override
    public User getUser(User user) {
        try {
            Connection connection = database.connect();
            String query = "SELECT * FROM person WHERE name = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                newUser = new User(
                        resultSet.getString(3),
                        resultSet.getString(2)
                );
            }
            connection.close();
            return newUser;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createUser(User user) {
        try {
            Connection connection = database.connect();
            String query = "INSERT INTO person (name, password) VALUES (?, ?);";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());

            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("created new user");
    }

    @Override
    public boolean userExist(User user) {
        if(user.getUsername() == this.getUser(user).getUsername() || user.getPassword() == this.getUser(user).getPassword()) {
            System.out.println("User exists already");
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void updateUser() {
        try {
            Connection connection = database.connect();
            String query = "UPDATE person SET Bio = ? Image = ? WHERE name = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            //preparedStatement.setString();
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
