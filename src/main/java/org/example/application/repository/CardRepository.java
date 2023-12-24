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

    private Connection connection;
    private PreparedStatement preparedStatement;
    @Override
    public void createCard(Card card) {
        try {
            connection = database.connect();
            String query = "INSERT INTO card (id, name, damage, type, element) VALUES (?, ?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, card.getId());
            preparedStatement.setString(2, card.getName());
            preparedStatement.setString(3, card.getDamage());
            preparedStatement.setString(4, card.getType());
            preparedStatement.setString(5, card.getElement());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public boolean enoughCardsExist() {
        try {
            connection = database.connect();
            String query = "SELECT COUNT(*) FROM card WHERE username IS NULL;";

            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int rowCount = resultSet.getInt(1);
                if(rowCount > 4) {
                    return true;
                } else {
                    System.out.println("There are not enough cards saved in the database");
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.closeConnection();
        }

        return false;
    }

    public ArrayList<Card> getPackage() {
        try {
            ArrayList<Card> packages = new ArrayList<>();
            Card result = null;
            connection = database.connect();
            String query = "SELECT * FROM card LIMIT 5;";

            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = new Card(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                );
                packages.add(result) ;          }
            return packages;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public void saveUsername(String username, ArrayList<Card> packages) {
        try {
            for(Card card : packages) {
                connection = database.connect();
                String query = "UPDATE card SET username = ? WHERE id = ?;";

                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, card.getId());
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.closeConnection();
        }

    }

    @Override
    public Card getCardByUsername(String username) {
        try {
            Card result = null;
            connection = database.connect();
            String query = "SELECT * FROM card WHERE username = ?;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = new Card(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                );
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.closeConnection();
        }
    }

    public ArrayList<Card> getStack(String username) {
        try {
            ArrayList<Card> stack = new ArrayList<>();
            Card result = null;
            connection = database.connect();
            String query = "SELECT * FROM card WHERE username = ?;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = new Card(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                );
                stack.add(result) ;
            }
            return stack;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.closeConnection();
        }
    }

    public ArrayList<Card> unconfigDeck(String username) {
        try {
            ArrayList<Card> deck = new ArrayList<>();
            Card result = null;
            connection = database.connect();
            String query = "SELECT * FROM card WHERE username = ? LIMIT 4;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = new Card(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                );
                deck.add(result) ;          }
            return deck;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.closeConnection();
        }
    }

    public Card configDeck(String username, String id) {
        try {
            Card result = null;
            connection = database.connect();
            String query = "SELECT * FROM card WHERE username = ? AND id = ?;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = new Card(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                );
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.closeConnection();
        }
    }

    public void closeConnection() {
        try {
            if(connection != null && !connection.isClosed()) {
                connection.close();
            }
            if(preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
