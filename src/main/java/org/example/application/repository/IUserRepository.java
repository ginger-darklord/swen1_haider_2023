package org.example.application.repository;

import org.example.application.models.User;

public interface IUserRepository {
    public User getUser(User user);
    public void createUser(User user);
    public void updateUser(User user);

}
