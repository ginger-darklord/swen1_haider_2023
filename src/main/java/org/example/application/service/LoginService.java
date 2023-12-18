package org.example.application.service;

import org.example.application.models.Card;
import org.example.application.models.User;
import org.example.application.repository.UserRepository;

import java.util.Scanner;

public class LoginService {
    private UserRepository userRepository = new UserRepository();
    private BattleService battleService;
    public boolean login(User user) {
        if(userRepository.getUserWithName(user).getUsername().equals(user.getUsername()) && userRepository.getUserWithName(user).getPassword().equals(user.getPassword())) {
            System.out.println("Login successful");
            System.out.println("You are logged in as: " + user.getUsername());
            return true;
        } else {
            return false;
        }
    }

    public boolean adminTokenLogin(String authorization) {
        if (authorization.equals("Authorization: Bearer admin-mtcgToken")) {
            System.out.println("login as admin successful");
            return true;
        } else {
            return false;
        }
    }

    public boolean userTokenLogin(String authorization) {
        if(authorization.equals("Authorization: Bearer kienboec-mtcgToken")) {
            return true;
        } else if (authorization.equals("Authorization: Bearer altenhof-mtcgToken")) {
            return true;
        } else {
            return false;
        }
    }

}
