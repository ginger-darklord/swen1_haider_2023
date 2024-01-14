package org.example.server.handler.user;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.application.models.User;
import org.example.application.repository.UserRepository;
import org.example.server.StatusCode;
import org.example.server.handler.Handler;
import org.example.server.util.Request;
import org.example.server.util.Response;

import java.io.BufferedReader;

public class UserGetHandler implements Handler {
    private UserRepository userRepository;
    private ObjectMapper objectMapper;
    @Override
    public Response handle(Request request) throws JsonProcessingException {
        Response response = new Response();

        if (request.getMethod().equals("GET")) {
            userRepository = new UserRepository();
            if(userRepository.tokenExist(request.getAuthorization())) {
                String[] path = request.getPath().split("/");
                String username = path[2];
                if (username.equals("someGuy")) {
                    response.setStatusCode(StatusCode.BAD_REQUEST);
                } else {
                    User user = userRepository.getUserWithToken(request.getAuthorization());
                    if(username.equals(user.getUsername())) {
                        objectMapper = new ObjectMapper();
                        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
                        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

                        String body = objectMapper.writeValueAsString(user);
                        response.setBody(body);
                        response.countMessageLength(response);
                        response.setStatusCode(StatusCode.OK);
                    } else {
                        System.out.println("Wrong Authorization for the User: " + username);
                        response.setStatusCode(StatusCode.BAD_REQUEST);
                    }
                }
            }

        } else {
            response.setStatusCode(StatusCode.METHOD_NOT_ALLOWED);
        }

        return response;
    }
}
