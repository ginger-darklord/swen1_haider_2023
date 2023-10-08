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
    public Response handle(Request request, BufferedReader bufferedReader) throws IOException {
        Response response = new Response();

        if(request.getMethod().equals("POST")) {
            System.out.println("Entering UserPostHandler");
            if(request.getHeaders() != null && request.getHeaders().containsKey("Content-Type")) {
                request.setContentType(request.getHeaders().get("Content-Type"));
                String contentType = request.getContentType();
                if(contentType != null && contentType.startsWith("application/json")) {
                    //reads json data and saves it in a Stringbuilder(sequence of characters)//
                    StringBuilder requestBody = new StringBuilder();
                    String line = bufferedReader.readLine();
                    System.out.println("line: " + line); //does not print
                    while (line != null) {
                        requestBody.append(line);
                        System.out.println("appends line");
                        try {
                            line = bufferedReader.readLine();
                            System.out.println("reads line: " + line);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    //parse json data using jackson//
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
                    System.out.println("objectMapper created");
                    try {
                        User user = objectMapper.readValue(requestBody.toString(), User.class);
                        UserRepository userRepository = new UserRepository(user);
                        System.out.println("userrepository created");
                        userRepository.createUser(user);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }


                    response.setStatusCode(StatusCode.OK);
                    System.out.println("Status-Code: " + response.getStatus());
                    System.out.println("Status-Message: " + response.getMessage());
                }
            }

        } else {
            response.setStatusCode(StatusCode.METHOD_NOT_ALLOWED);
            System.out.println("Status-Code: " + response.getStatus());
            System.out.println("Status-Message: " + response.getMessage());
        }

        return response;
    }
}
