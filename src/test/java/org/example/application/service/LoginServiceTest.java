package org.example.application.service;

import org.example.application.models.User;
import org.example.application.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {

    private LoginService loginService = new LoginService();
    private UserRepository userRepository = new UserRepository();

    @Test
    public void testAdminTokenLogin() {
        String authorization = "Authorization: Bearer admin-mtcgToken";
        User user = new User("admin", "istrator", "Authorization: Bearer admin-mtcgToken", 20);
        userRepository.createUser(user);
        boolean admin = loginService.adminTokenLogin(authorization);

        assertEquals(true, admin);
    }

    @Test
    public void testFailedUserLogin() {
        User user = new User("dye", "jane", "Authorization: Bearer dye-mtcgToken", 20);

        boolean login = loginService.login(user);
        assertEquals(false, login);
    }

    @Test
    public void testUserTokenLogin() {
        User user = new User("dear", "jane", "Authorization: Bearer dear-mtcgToken", 20);
        userRepository.createUser(user);
        boolean login = loginService.userTokenLogin(user.getToken());

        assertEquals(true, login);
    }

    @Test
    public void testUserExists() {
        User user = new User("meyer", "jennie", "Authorization: Bearer meyer-mtcgToken", 20);
        userRepository.createUser(user);
        boolean exists = loginService.userExist(user);

        assertEquals(true, exists);
    }


}