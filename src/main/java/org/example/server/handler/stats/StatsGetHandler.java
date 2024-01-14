package org.example.server.handler.stats;

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

import java.io.IOException;

public class StatsGetHandler implements Handler {
    @Override
    public Response handle(Request request) throws JsonProcessingException, IOException {
        Response response = new Response();
        if(request.getMethod().equals("GET")) {
            UserRepository userRepository = new UserRepository();
            if(userRepository.tokenExist(request.getAuthorization())) {
                User user = userRepository.getUserWithToken(request.getAuthorization());
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
                objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

                String stats = objectMapper.writeValueAsString(user.getElo());
                response.setBody(stats);
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
}