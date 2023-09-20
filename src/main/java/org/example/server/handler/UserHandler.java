package org.example.server.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.server.Request;
import org.example.server.Response;

public class UserHandler implements Handler{
    @Override
    public Response handle(Request request) throws JsonProcessingException {
        if(request.getMethod().equals("POST")) {
            String contentType = request.getRequest();
            if(contentType != null && contentType.startsWith("Content-Type: application/json")) {
                //reads json data and saves it in a Stringbuilder(sequence of characters)//
                StringBuilder requestBody = new StringBuilder();
                String line = request.getRequest();
                while (line != null) {
                    requestBody.append(line);
                }

                //parse json data using jackson//
                ObjectMapper objectMapper = new ObjectMapper();
                Response response = objectMapper.readValue(line , Response.class);

            }

        } else if (request.getMethod().equals("GET")) {
            System.out.println("Get method was acknowledged");
            //database things ig?
        } else if (request.getMethod().equals("PUT")) {
            System.out.println("put method was acknowledged");
        } else if (request.getMethod().equals("DELETE")) {
            System.out.println("delete method was acknowledged");
        }

        return null;
    }
}
