package org.example.application.repository;

import org.example.application.models.Card;
import org.example.server.util.Response;

import java.util.ArrayList;
import java.util.List;

public interface ICardRepository {
    public void createCard(Card card);
    public ArrayList<Card> getPackage();
    public void saveUsername(String username, ArrayList<Card> packages);
    public Card getCardByUsername(String username);

    public boolean enoughCardsExist();
}
