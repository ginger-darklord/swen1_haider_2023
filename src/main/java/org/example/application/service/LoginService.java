package org.example.application.service;

import org.example.application.models.Card;
import org.example.application.models.User;
import org.example.application.repository.UserRepository;

import java.util.Scanner;

public class LoginService {
    private UserRepository userRepository = new UserRepository();
    public boolean login(User user) {
        if(userRepository.getUserWithName(user) == null) {
            return false;
        }
        if(userRepository.getUserWithName(user).getUsername().equals(user.getUsername()) && userRepository.getUserWithName(user).getPassword().equals(user.getPassword())) {
            System.out.println("Login successful");
            System.out.println("You are logged in as: " + user.getUsername());
            return true;
        } else {
            return false;
        }
    }

    public boolean adminTokenLogin(String authorization) {
        if (userRepository.tokenExist(authorization)) {
            User admin = userRepository.getUserWithToken(authorization);
            if (admin.getUsername().equals("admin") && admin.getToken().equals("Authorization: Bearer " + admin.getUsername() + "-mtcgToken")) {
                System.out.println("login as admin successful");
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean userTokenLogin(String authorization) {
        if (userRepository.tokenExist(authorization)) {
            User user = userRepository.getUserWithToken(authorization);
            if(user.getToken().equals("Authorization: Bearer " + user.getUsername() + "-mtcgToken")) {
                System.out.println("You are logged in as: " + user.getUsername());
                return true;
            } else {
                System.out.println("Wrong username or token");
                return false;
            }
        } else {
            System.out.println(authorization + " does not exist as token.");
            return false;
        }
    }

    public boolean userExist(User user) {
        if (userRepository.getUserWithName(user) == null) {
            return false;
        } else if (user.getUsername().equals(userRepository.getUserWithName(user).getUsername()) || user.getPassword().equals(userRepository.getUserWithName(user).getPassword())) {
            System.out.println("User exists already");
            return true;
        } else {
            return false;
        }

    }

}
