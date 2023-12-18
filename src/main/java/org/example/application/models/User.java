package org.example.application.models;

import org.example.application.models.Card;

import java.util.ArrayList;
import java.util.Scanner;

public class User {
    private String Username;
    private String Password;
    private int coin;
    private String Bio;
    private String Image;

    private ArrayList<Card> deck; //nur max 4
    private ArrayList<Card> stack;
    private String token;

    public User() {
    }

    public User(String password, String username) {
        this.Password = password;
        this.Username = username;
    }

    public User(String username, String password, int coin) {
        this.Password = password;
        this.Username = username;
        this.coin = coin;
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

    public void saveCardsStack(Card card) {
        this.stack = new ArrayList<>();
        this.stack.add(card);
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

    public void showStack(User user) {
        System.out.println("-----------------------------");
        System.out.println("All Cards in the Stack:");
        if(user.stack == null) {
            System.out.println("There are no cards in the stack");
        } else {
            for (Card stackPart : user.stack) {
                System.out.println("----------------------------------------");
                System.out.println("Id: " + stackPart.getId());
                System.out.println("Name: " + stackPart.getName());
                System.out.println("Damage: " + stackPart.getDamage());
                System.out.println("-----------------------------------------");
            }
        }
    }

    public void showDeck(User user) {
        //output only in json
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

    public void configureDeck(User user) {
        //4 strongest cards should be used
    }
}
