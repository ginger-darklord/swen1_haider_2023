package org.example.server.handler.user;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.application.models.User;
import org.example.application.repository.UserRepository;
import org.example.server.StatusCode;
import org.example.server.handler.Handler;
import org.example.server.util.Request;
import org.example.server.util.Response;

import java.io.IOException;

public class UserPutHandler implements Handler {

    private UserRepository userRepository;
    private ObjectMapper objectMapper;
    @Override
    public Response handle(Request request) throws JsonProcessingException, IOException {
        Response response = new Response();

        if(request.getMethod().equals("PUT")) {
            if(request.getContentType().startsWith("Content-Type: ")) {
                userRepository = new UserRepository();
                if(userRepository.tokenExist(request.getAuthorization())) {
                    String[] path = request.getPath().split("/");
                    String username = path[2];
                    User user = userRepository.getUserWithToken(request.getAuthorization());
                    if(username.equals(user.getUsername())) {
                        objectMapper = new ObjectMapper();
                        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
                        User update = objectMapper.readValue(request.getBody(), User.class);
                        userRepository.updateUser(update, username);
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
