package org.example.server.handler.deck;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.application.models.Card;
import org.example.application.models.User;
import org.example.application.repository.CardRepository;
import org.example.application.repository.UserRepository;
import org.example.server.StatusCode;
import org.example.server.handler.Handler;
import org.example.server.util.Request;
import org.example.server.util.Response;

import java.io.BufferedReader;
import java.util.ArrayList;

public class DeckGetHandler implements Handler {
    private UserRepository userRepository;
    private CardRepository cardRepository;
    private ObjectMapper objectMapper;
    @Override
    public Response handle(Request request) throws JsonProcessingException {
        Response response = new Response();
        if(request.getMethod().equals("GET")) {
            userRepository = new UserRepository();
            if(userRepository.tokenExist(request.getAuthorization())) {
                objectMapper = new ObjectMapper();
                objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
                objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

                User user = userRepository.getUserWithToken(request.getAuthorization());
                cardRepository = new CardRepository();
                ArrayList<Card> deck = cardRepository.unconfigDeck(user.getUsername());

                String body = objectMapper.writeValueAsString(deck);
                response.setBody(body);
                response.countMessageLength(response);
                response.setStatusCode(StatusCode.OK);
            } else {
                response.setStatusCode(StatusCode.BAD_REQUEST);
            }
        } else {
            response.setStatusCode(StatusCode.METHOD_NOT_ALLOWED);
        }
        return response;
    }
}
