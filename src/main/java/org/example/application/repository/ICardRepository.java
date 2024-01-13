package org.example.application.repository;

import org.example.application.models.Card;
import org.example.application.models.User;
import org.example.server.util.Response;

import java.util.ArrayList;
import java.util.List;

public interface ICardRepository {
    public void createCard(Card card);
    public boolean enoughCardsExist();
    public ArrayList<Card> getPackage();
    public void saveUsername(String username, ArrayList<Card> packages);
    public Card getCardByUsername(String username);
    public ArrayList<Card> getStack(String username);
    public ArrayList<Card> unconfigDeck(String username);
    public Card configDeck(String username, String id);
    public void addToDeck(Card card, User user);
}
