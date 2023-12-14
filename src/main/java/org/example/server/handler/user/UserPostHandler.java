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

    private UserRepository userRepository;
    private  ObjectMapper objectMapper;
    public UserPostHandler() {}

    //only using it for the testing class
    public UserPostHandler(UserRepository userRepository, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }
    @Override
    public Response handle(Request request) throws IOException {
        Response response = new Response();

        if(request.getMethod().equals("POST")) {
            System.out.println("Entering UserPostHandler");
            if(request.getContentType().startsWith("Content-Type: ")) {
                String body = request.getBody();

                //parse json data using jackson//
                objectMapper = new ObjectMapper();
                objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
                try {
                    User user = objectMapper.readValue(body , User.class);
                    userRepository = new UserRepository();
                    if(!userRepository.userExist(user)) {
                        userRepository.createUser(user);
                    }
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
