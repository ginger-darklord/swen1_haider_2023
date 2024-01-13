package org.example.application.repository;

import org.checkerframework.checker.units.qual.C;
import org.example.application.models.Card;
import org.example.application.models.User;
import org.example.server.Database;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CardRepositoryTest {
    private CardRepository cardRepository;
    @BeforeEach
    public void setUp() {
        cardRepository = new CardRepository();
    }

    @Test
    public void testEnoughCardsExists() {
        Card card = new Card("1232", "Chimera", "40", "monster", "fire");
        Card card1 = new Card("123", "FireSpell", "40", "spell", "fire");
        Card card2 = new Card("125", "WaterSpell", "40", "spell", "water");
        Card card3 = new Card("124", "Dragon", "40", "monster", "normal");
        Card card4 = new Card("126", "Goblin", "40", "monster", "water");

        cardRepository.createCard(card);
        cardRepository.createCard(card1);
        cardRepository.createCard(card2);
        cardRepository.createCard(card3);
        cardRepository.createCard(card4);

        boolean result = cardRepository.enoughCardsExist();
        assertEquals(true, result);
    }

    @Test
    public void testGetPackage() {
        Card card = new Card("127", "Chimera", "40", "monster", "fire");
        Card card1 = new Card("128", "FireSpell", "40", "spell", "fire");
        Card card2 = new Card("129", "WaterSpell", "40", "spell", "water");
        Card card3 = new Card("130", "Dragon", "40", "monster", "normal");
        Card card4 = new Card("136", "Goblin", "40", "monster", "water");

        cardRepository.createCard(card);
        cardRepository.createCard(card1);
        cardRepository.createCard(card2);
        cardRepository.createCard(card3);
        cardRepository.createCard(card4);

        ArrayList<Card> result = cardRepository.getPackage();
        assertEquals(5, result.size());
    }

    @Test
    public void testGetStack() {
        Card card = new Card("134", "Ork", "30", "monster", "fire");
        Card card2 = new Card("135", "Fox", "40", "monster", "water");
        ArrayList<Card> list = new ArrayList<>();
        list.add(card);
        list.add(card2);

        cardRepository.createCard(card);
        cardRepository.createCard(card2);
        cardRepository.saveUsername("susi", list);
        ArrayList<Card> stack = cardRepository.getStack("susi");

        assertEquals(list.size(), stack.size());
    }



}