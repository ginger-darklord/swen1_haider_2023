package org.example.application.models;

import org.example.application.models.Card;

import java.util.ArrayList;
import java.util.Scanner;

public class User {
    private String Username;
    private String Password;
    private String Name;
    private int coin;
    private String Bio;
    private String Image;

    private int elo;

    private ArrayList<Card> deck; //nur max 4
    private ArrayList<Card> stack;
    private String token;

    public User() {
        this.stack = new ArrayList<>();
    }

    public User (String username, String password,String token, int coin) {
        this.Password = password;
        this.Username = username;
        this.token = token;
        this.coin = coin;
    }
    public User(String username, String password,String token, int coin, String bio, String image, String name, int elo) {
        this.Password = password;
        this.Username = username;
        this.token = token;
        this.coin = coin;
        this.stack = new ArrayList<>();
        this.Bio = bio;
        this.Image = image;
        this.Name = name;
        this.elo = elo;
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

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public int getCoin() {return this.coin;}
    public void setCoin(int coin) {
        this.coin = coin;
    }

    public void saveInStack(ArrayList<Card> packages, User user) {
        for(Card packagePart : packages) {
            user.stack.add(packagePart);
        }
    }

    public String getBio() {
        return Bio;
    }

    public void setBio(String bio) {
        this.Bio = bio;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        this.Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getElo() {
        return elo;
    }

    public void setElo(int elo) {
        this.elo = elo;
    }

    public void showDeck(User user) {
        System.out.println("-----------------------------");
        System.out.println("All Cards in the Deck:");
        if(user.deck == null) {
            System.out.println("There are no cards in the deck");
        } else {
            for (Card deckPart : user.deck) {
                System.out.println("----------------------------------------");
                System.out.println("Id: " + deckPart.getId());
                System.out.println("Name: " + deckPart.getName());
                System.out.println("Damage: " + deckPart.getDamage());
                System.out.println("-----------------------------------------");

            }
        }
    }
}
