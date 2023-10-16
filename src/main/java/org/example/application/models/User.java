package org.example.application.models;

import org.example.application.models.Card;

import java.util.ArrayList;

public class User {
    private String Username;
    private String Password;
    private int coin = 20;
    private ArrayList<Card> deck; //nur max 4
    private ArrayList<Card> stack;

    public User() {
    }

    public User(String password, String username) {
        this.Password = password;
        this.Username = username;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }
}
