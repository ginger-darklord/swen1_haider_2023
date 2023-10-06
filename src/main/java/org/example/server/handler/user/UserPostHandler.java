package org.example.server.handler.user;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.application.models.User;
import org.example.application.repository.UserRepository;
import org.example.server.handler.Handler;
import org.example.server.util.Request;
import org.example.server.util.Response;
import org.example.server.StatusCode;

import java.io.IOException;

public class UserPostHandler implements Handler {
    @Override
    public Response handle(Request request) throws JsonProcessingException {
        if(request.getMethod().equals("POST")) {
            System.out.println("Method: " + request.getMethod());
            String contentType = request.getRequest();
            if(contentType != null && contentType.startsWith("Content-Type: application/json")) {
                //reads json data and saves it in a Stringbuilder(sequence of characters)//
                StringBuilder requestBody = new StringBuilder();
                String line = request.getRequest();
                while (line != null) {
                    requestBody.append(line);
                    line = request.getRequest();
                }

                //parse json data using jackson//
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
                try {
                    User user = objectMapper.readValue(requestBody.toString(), User.class);
                    UserRepository userRepository = new UserRepository(user);
                    //ruft die repository funktion auf
                    //darf er so?
                    userRepository.createUser(user);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Response response = new Response();
                response.setStatusCode(StatusCode.OK);
                System.out.println("Post handled well");
                return response;
            }

        }

        Response response = new Response();
        response.setStatusCode(StatusCode.METHOD_NOT_ALLOWED);
        return response;
    }
}
