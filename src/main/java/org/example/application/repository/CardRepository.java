package org.example.application.repository;

import org.example.application.models.Card;
import org.example.server.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ListIterator;

public class CardRepository implements ICardRepository{
    private Card card;
    private Database database = new Database();
    private ArrayList<Card> packages;

    @Override
    public void createCard(Card card) {
        try {
            Connection connection = database.connect();
            String query = "INSERT INTO card (id, name, damage) VALUES (?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, card.getId());
            preparedStatement.setString(2, card.getName());
            preparedStatement.setString(3, card.getDamage());

            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createPackage(Card card){

        ListIterator<Card> iterator = packages.listIterator();
        while (iterator.hasNext()) {
            createCard(iterator.next());
            System.out.println("package was sent to createCard and database");
        }
    }
}
