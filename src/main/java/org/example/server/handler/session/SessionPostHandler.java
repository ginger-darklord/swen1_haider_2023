package org.example.server.handler.session;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.application.models.User;
import org.example.application.repository.UserRepository;
import org.example.application.service.LoginService;
import org.example.server.StatusCode;
import org.example.server.handler.Handler;
import org.example.server.util.Request;
import org.example.server.util.Response;

import java.io.BufferedReader;
import java.io.IOException;

public class SessionPostHandler implements Handler {
    @Override
    public Response handle(Request request) throws JsonProcessingException {
        Response response = new Response();

        if(request.getMethod().equals("POST")) {
            String contentType = request.getContentType();
            if(contentType != null && contentType.startsWith("Content-Type: application/json")) {
                String body = request.getBody();

                //parse json data using jackson//
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
                try {
                    User user = objectMapper.readValue(body , User.class);
                    LoginService loginService = new LoginService();
                    if(loginService.login(user) == false) {
                        response.setStatusCode(StatusCode.NOT_FOUND);
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
