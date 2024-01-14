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

    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository = new UserRepository();
    }

    @Test
    public void testGetUserWithName() {
        User user = new User("doe", "jane","Authorization: Bearer doe-mtcgToken", 20);
        userRepository.createUser(user);
        User result = userRepository.getUserWithName(user);

        assertEquals(user.getUsername(), result.getUsername());
        assertEquals(user.getPassword(), result.getPassword());
    }

    @Test
    public void testGetUserWithToken() {
        User user = new User("muller", "alice", "Authorization: Bearer muller-mtcgToken", 20);
        userRepository.createUser(user);
        User result = userRepository.getUserWithToken(user.getToken());

        assertEquals(user.getToken(), result.getToken());
    }

    @Test
    public void testTokenExists() {
        User user = new User("maier", "isabel", "Authorization: Bearer maier-mtcgToken", 20);
        userRepository.createUser(user);
        boolean exists = userRepository.tokenExist(user.getToken());

        assertEquals(true, exists);
    }


}