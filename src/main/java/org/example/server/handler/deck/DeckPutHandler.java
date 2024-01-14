package org.example.server.handler.deck;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.application.models.Card;
import org.example.application.models.User;
import org.example.application.repository.CardRepository;
import org.example.application.repository.UserRepository;
import org.example.server.StatusCode;
import org.example.server.handler.Handler;
import org.example.server.util.Request;
import org.example.server.util.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class DeckPutHandler implements Handler {
    private UserRepository userRepository;
    private CardRepository cardRepository;
    private ObjectMapper objectMapper;
    @Override
    public Response handle(Request request) throws JsonProcessingException, IOException {
        Response response = new Response();
        if (request.getMethod().equals("PUT")) {
            if (request.getContentType().startsWith("Content-Type: application/json")) {
                userRepository = new UserRepository();
                cardRepository = new CardRepository();
                if(userRepository.tokenExist(request.getAuthorization())) {
                    User user = userRepository.getUserWithToken(request.getAuthorization());
                    objectMapper = new ObjectMapper();
                    objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
                    String[] body = objectMapper.readValue(request.getBody(), String[].class);
                    if(body.length == 4) {
                        ArrayList<Card> deck = new ArrayList<>();
                        if(cardRepository.deckFull(user.getUsername())) {
                            response.setStatusCode(StatusCode.BAD_REQUEST);
                        } else {
                            //saving configured deck in db
                            for (String id : body) {
                                Card card = cardRepository.configDeck(user.getUsername(), id);
                                deck.add(card);
                                cardRepository.addToDeck(card, user);
                            }
                            String responseBody = objectMapper.writeValueAsString(deck);
                            response.setBody(responseBody);
                            response.countMessageLength(response);
                            response.setStatusCode(StatusCode.OK);
                        }

                    } else {
                        System.out.println("Not enough card ids for a deck, has to be 4");
                        response.setStatusCode(StatusCode.BAD_REQUEST);
                    }
                } else {
                    response.setStatusCode(StatusCode.BAD_REQUEST);
                }
            } else {
                response.setStatusCode(StatusCode.BAD_REQUEST);
            }
        } else {
            response.setStatusCode(StatusCode.METHOD_NOT_ALLOWED);
        }
        return response;
    }
}
