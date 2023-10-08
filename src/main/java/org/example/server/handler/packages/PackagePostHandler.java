package org.example.server.handler.packages;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.server.StatusCode;
import org.example.server.handler.Handler;
import org.example.server.util.Request;
import org.example.server.util.Response;

import java.io.BufferedReader;

public class PackagePostHandler implements Handler {
    @Override
    public Response handle(Request request, BufferedReader bufferedReader) throws JsonProcessingException {
        if(request.getMethod().equals("POST")) {
            String contentType = request.getContentType();
            if(contentType != null && contentType.startsWith("Content-Type: application/json")) {

            }
        }
        Response response = new Response();
        response.setStatusCode(StatusCode.METHOD_NOT_ALLOWED);
        return response;
    }
}
