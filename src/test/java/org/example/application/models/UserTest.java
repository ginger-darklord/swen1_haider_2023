package org.example.application.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    public void testUserGetterAndSetter() {
        User user = new User();

        user.setUsername("lena");
        user.setPassword("maier");
        String username = user.getUsername();
        String password = user.getPassword();

        assertEquals("lena", username);
        assertEquals("maier", password);
    }
}