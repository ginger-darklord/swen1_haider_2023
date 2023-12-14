package org.example.application.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    public void testCardGetterAndSetter() {
        //Arrange
        Card card = new Card();
        card.setId("123456789");
        card.setName("Chimera");
        card.setDamage("70");

        //Act
        String id = card.getId();
        String name = card.getName();
        String damage = card.getDamage();

        //Assert
        assertEquals("123456789", id);
        assertEquals("Chimera", name);
        assertEquals("70", damage);
    }
}