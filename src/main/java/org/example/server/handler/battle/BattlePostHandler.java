package org.example.server.handler.battle;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.application.models.User;
import org.example.application.repository.UserRepository;
import org.example.server.StatusCode;
import org.example.server.handler.Handler;
import org.example.server.util.Request;
import org.example.server.util.Response;

import java.io.IOException;

public class BattlePostHandler implements Handler {
    private final Object lock = new Object();

    @Override
    public Response handle(Request request) throws JsonProcessingException, IOException {
        Response response = new Response();
        if(request.getMethod().equals("POST")) {
            UserRepository userRepository = new UserRepository();
            if(userRepository.tokenExist(request.getAuthorization())) {
                User user = userRepository.getUserWithToken(request.getAuthorization());
                //
            } else {
                response.setStatusCode(StatusCode.NOT_FOUND);
            }
        } else {
            response.setStatusCode(StatusCode.METHOD_NOT_ALLOWED);
        }

        return response;
    }
}
