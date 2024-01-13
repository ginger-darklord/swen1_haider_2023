package org.example.application.repository;

import org.example.application.models.User;

import java.util.ArrayList;

public interface IUserRepository {
    public User getUserWithName(User user);
    public User getUserWithToken(String token);
    public void createUser(User user);
    public boolean tokenExist(String token);
    public void updateUser(User user, String username);
    public void buyWithCoin(int numberOfCoin, User user);
    public ArrayList<Integer> getScoreboard();
    public void battleReady(User user, boolean ready);
}
