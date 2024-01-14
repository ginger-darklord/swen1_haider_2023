package org.example.server.handler.card;

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
import java.io.File;
import java.util.ArrayList;

public class CardGetHandler implements Handler {
    private UserRepository userRepository;
    private CardRepository cardRepository;
    private ObjectMapper objectMapper;
    @Override
    public Response handle(Request request) throws JsonProcessingException {
        Response response = new Response();
        Card card = null;

        if(request.getMethod().equals("GET")) {
            userRepository = new UserRepository();
            if(userRepository.tokenExist(request.getAuthorization())) {
                objectMapper = new ObjectMapper();
                objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
                objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

                cardRepository = new CardRepository();
                User user = userRepository.getUserWithToken(request.getAuthorization());
                ArrayList<Card> stack = cardRepository.getStack(user.getUsername());
                this.showCards(stack);

                String body = objectMapper.writeValueAsString(stack);
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

    public void showCards(ArrayList<Card> stack) {
        System.out.println("Amount of cards: " + stack.size());
        for (Card part : stack) {
            System.out.println("----------------------------------------");
            System.out.println("Id: " + part.getId());
            System.out.println("Name: " + part.getName());
            System.out.println("Damage: " + part.getDamage());
            System.out.println("Type: " + part.getType());
            System.out.println("Element: " + part.getElement());
            System.out.println("-----------------------------------------");
        }
    }

}
