package org.example.application.service;

import org.example.application.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {

    private LoginService loginService = new LoginService();

    @Test
    public void testAdminLogin() {
        String authorization = "Authorization: Bearer admin-mtcgToken";
        boolean admin = loginService.adminTokenLogin(authorization);

        assertEquals(true, admin);
    }

    //ToDo login for usernames test

}