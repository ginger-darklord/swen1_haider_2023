package org.example.application.models;

import org.example.application.models.Card;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private int coin;
    private ArrayList<Card> deck; //nur max 4
    private ArrayList<Card> stack;

    User() {
        this.coin = 20;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
