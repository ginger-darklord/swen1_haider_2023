package org.example.server.handler.user;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.application.models.User;
import org.example.application.repository.UserRepository;
import org.example.server.handler.Handler;
import org.example.server.util.Request;
import org.example.server.util.Response;
import org.example.server.StatusCode;

import java.io.BufferedReader;
import java.io.IOException;

public class UserPostHandler implements Handler {
    @Override
    public Response handle(Request request) throws IOException {
        Response response = new Response();

        if(request.getMethod().equals("POST")) {
            System.out.println("Entering UserPostHandler");
            if(request.getContentType().startsWith("Content-Type: ")) {
                String contentType = request.getContentType();
                String body = request.getBody();

                //parse json data using jackson//
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
                try {
                    User user = objectMapper.readValue(body , User.class); //user.class is wrong
                    UserRepository userRepository = new UserRepository(user);
                    userRepository.createUser(user);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                response.setStatusCode(StatusCode.OK);
            }

        } else {
            response.setStatusCode(StatusCode.METHOD_NOT_ALLOWED);
        }

        return response;
    }
}