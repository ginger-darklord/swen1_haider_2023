package org.example.server.handler.session;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.application.models.User;
import org.example.application.repository.UserRepository;
import org.example.application.service.BattleService;
import org.example.application.service.LoginService;
import org.example.server.StatusCode;
import org.example.server.handler.Handler;
import org.example.server.util.Request;
import org.example.server.util.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class SessionPostHandler implements Handler {
    private Response response;
    private LoginService loginService;
    private UserRepository userRepository;
    @Override
    public Response handle(Request request) throws JsonProcessingException {
        response = new Response();

        if(request.getMethod().equals("POST")) {
            String contentType = request.getContentType();
            if(contentType != null && contentType.startsWith("Content-Type: application/json")) {
                String body = request.getBody();

                //parse json data using jackson//
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
                try {
                    User user = objectMapper.readValue(body , User.class);
                    loginService = new LoginService();
                    if(!loginService.login(user)) {
                        response.setStatusCode(StatusCode.BAD_REQUEST);
                    } else {
                        response.setStatusCode(StatusCode.OK);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            response.setStatusCode(StatusCode.METHOD_NOT_ALLOWED);
        }
        return response;
    }
}
