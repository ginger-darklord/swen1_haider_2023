package org.example.application.repository;

import org.checkerframework.checker.units.qual.C;
import org.example.application.models.Card;
import org.example.server.Database;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CardRepositoryTest {
    private CardRepository cardRepository = new CardRepository();
    private Connection connection;
    private Database database = new Database();

    @BeforeEach
    void setUp() throws SQLException {
        connection = database.connect();
    }

    @AfterEach
    void tearDown() throws SQLException {
        if(connection != null) {
            connection.close();
        }
    }

    @Test
    public void testCreateAndGetCard() {
        //toDO write differently
        Card card = new Card("1234567892", "Chimera", "40");
        cardRepository.createCard(card);
        Card result = cardRepository.getCard(card.getDamage());

        assertEquals(card.getId(), result.getId());
        assertEquals(card.getName(), result.getName());
        assertEquals(card.getDamage(), result.getDamage());
    }

}