package org.example.server.handler.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.server.StatusCode;
import org.example.server.handler.Handler;
import org.example.server.util.Request;
import org.example.server.util.Response;

public class UserGetHandler implements Handler {
    @Override
    public Response handle(Request request) throws JsonProcessingException {
        if(request.getMethod().equals("GET")) {
            //smth with a token??
        }

        Response response = new Response();
        response.setStatusCode(StatusCode.METHOD_NOT_ALLOWED);
        return response;
    }
}
