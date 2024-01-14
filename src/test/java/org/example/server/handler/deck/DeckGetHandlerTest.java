package org.example.server.handler.deck;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.application.models.Card;
import org.example.application.models.User;
import org.example.application.repository.CardRepository;
import org.example.application.repository.UserRepository;
import org.example.server.handler.card.CardGetHandler;
import org.example.server.util.Request;
import org.example.server.util.Response;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DeckGetHandlerTest {
    @Test
    public void testHandle() throws JsonProcessingException {
        Request request = new Request();
        request.setPath("/deck");
        request.setMethod("GET");
        request.setAuthorization("Authorization: Bearer mewer-mtcgToken");

        User user = new User("mewer", "neda", "Authorization: Bearer mewer-mtcgToken", 20);
        UserRepository userRepository = new UserRepository();

        Card card = new Card("147", "High Ork",10, "monster", "normal");
        Card card2 = new Card("148", "Dragon",40, "monster", "fire");
        Card card3 = new Card("149", "Siren",30, "monster", "water");
        Card card4 = new Card("150", "Ork",20, "monster", "normal");
        Card card5 = new Card("151", "WaterSpell",55, "spell", "water");
        ArrayList<Card> list = new ArrayList<>();
        CardRepository cardRepository = new CardRepository();
        DeckGetHandler deckGetHandler = new DeckGetHandler();

        userRepository.createUser(user);
        cardRepository.createCard(card);
        cardRepository.createCard(card2);
        cardRepository.createCard(card3);
        cardRepository.createCard(card4);
        cardRepository.createCard(card5);
        list.add(card);
        list.add(card2);
        list.add(card3);
        list.add(card4);
        list.add(card5);
        cardRepository.saveUsername(user.getUsername(), list);
        Response response = deckGetHandler.handle(request);

        assertEquals("OK", response.getMessage());
        assertEquals(200, response.getStatus());
    }


}