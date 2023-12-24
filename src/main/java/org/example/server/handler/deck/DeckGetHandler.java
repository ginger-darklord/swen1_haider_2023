package org.example.server.handler.deck;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.application.models.User;
import org.example.application.repository.CardRepository;
import org.example.application.repository.UserRepository;
import org.example.server.StatusCode;
import org.example.server.handler.Handler;
import org.example.server.util.Request;
import org.example.server.util.Response;

import java.io.BufferedReader;

public class DeckGetHandler implements Handler {
    private UserRepository userRepository;
    private CardRepository cardRepository;
    @Override
    public Response handle(Request request) throws JsonProcessingException {
        Response response = new Response();
        if(request.getMethod().equals("GET")) {
            userRepository = new UserRepository();
            cardRepository = new CardRepository();
            if(userRepository.tokenExist(request.getAuthorization())) {
                User user = userRepository.getUserWithToken(request.getAuthorization());

            } else {
                response.setStatusCode(StatusCode.BAD_REQUEST);
            }
        } else {
            response.setStatusCode(StatusCode.METHOD_NOT_ALLOWED);
        }
        return response;
    }
}
