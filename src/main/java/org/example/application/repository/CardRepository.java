package org.example.application.repository;

import org.example.application.models.Card;
import org.example.server.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ListIterator;

public class CardRepository implements ICardRepository{
    private Card card;
    private Database database = new Database();

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

    public Card getCard(Card card) {
        try {
            Card result = null;
            Connection connection = database.connect();
            String query = "SELECT * FROM card WHERE name = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, card.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = new Card(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                );
            }

            connection.close();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
