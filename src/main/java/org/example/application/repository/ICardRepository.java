package org.example.application.repository;

import org.example.application.models.Card;
import org.example.application.models.User;
import org.example.server.util.Response;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ICardRepository {
    void createCard(Card card);
    boolean enoughCardsExist();
    ArrayList<Card> getPackage();
    void saveUsername(String username, ArrayList<Card> packages);
    Card getCardByUsername(String username);
    ArrayList<Card> getStack(String username);
    ArrayList<Card> unconfigDeck(String username);
    Card configDeck(String username, String id);
    void addToDeck(Card card, User user);
    ArrayList<Card> getDeck(String username);
    boolean deckFull(String username);
}
