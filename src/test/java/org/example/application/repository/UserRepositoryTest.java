package org.example.application.repository;

import org.example.application.models.User;
import org.example.server.Database;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    private UserRepository userRepository = new UserRepository();
    private Connection connection;
    private Database database = new Database();

    @BeforeEach
    void setUp() throws SQLException {
        connection = database.connect();
    }

    @AfterEach
    void tearDown() throws SQLException {
        if(connection != null) {
            connection.close();
        }
    }

    @Test
    public void testCreateAndGetUser() {
        User user = new User("doe", "jane");
        userRepository.createUser(user);
        User result = userRepository.getUserWithName(user);

        assertEquals(user.getUsername(), result.getUsername());
        assertEquals(user.getPassword(), result.getPassword());
    }

}