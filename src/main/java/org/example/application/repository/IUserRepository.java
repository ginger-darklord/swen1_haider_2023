package org.example.application.repository;

import org.example.application.models.User;

public interface IUserRepository {
    public User getUserWithName(User user);
    public User getUserWithToken(String token);
    public void createUser(User user);
    public boolean userExist(User user);
    public boolean tokenExist();
    public void updateUser(User user);
    public void buyWithCoin(int numberOfCoin, User user);
}
