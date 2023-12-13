package org.example.application.service;

import org.example.application.models.Card;
import org.example.application.models.User;
import org.example.application.repository.UserRepository;

public class LoginService {
    private UserRepository userRepository;
    public boolean login(User user) {
        userRepository = new UserRepository();

        if(userRepository.getUser(user).getUsername().equals(user.getUsername()) && userRepository.getUser(user).getPassword().equals(user.getPassword())) {
            System.out.println("Login successful");
            System.out.println("You are logged in as: " + user.getUsername());
            return true;
        } else {
            return false;
        }
    }

    public boolean adminLogin(String authorization) {
        if (authorization.equals("Authorization: Bearer admin-mtcgToken")) {
            System.out.println("login as admin successful");
            return true;
        } else {
            return false;
        }
    }
}
