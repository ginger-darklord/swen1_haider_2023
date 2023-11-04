package org.example.application.repository;

import org.example.application.models.Card;
import org.example.server.Database;

import java.sql.Connection;
import java.sql.SQLException;

public class CardRepository implements ICardRepository{
    private Card card;
    private Database database = new Database();

    @Override
    public Card getCard() {
        try {
            Connection connection = database.connect();
            String query = "SELECT FROM card WHERE token = ?";
            //toDO ask how tf im supposed to do this
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return card;
    }
}
