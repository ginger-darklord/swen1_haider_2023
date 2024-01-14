package org.example.application.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    public void testUserGetterAndSetter() {
        User user = new User();

        user.setUsername("lena");
        user.setPassword("maier");
        user.setToken("Authorization: Bearer maier-mtcgToken");

        assertEquals("lena", user.getUsername());
        assertEquals("maier", user.getPassword());
        assertEquals("Authorization: Bearer maier-mtcgToken", user.getToken());
    }
}