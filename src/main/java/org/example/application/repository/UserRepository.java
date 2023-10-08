package org.example.application.repository;

import org.example.application.models.User;

public class UserRepository implements IUserRepository {
    //sql with prepared statements
    private User user;

    public UserRepository(User user) {
        this.user = user;
    }

    @Override
    public User getUser(User user) {
        return user;
    }

    @Override
    public void createUser(User user) {
        System.out.println("create user function");
    }

    @Override
    public void updateUser(User user) {

    }
}
