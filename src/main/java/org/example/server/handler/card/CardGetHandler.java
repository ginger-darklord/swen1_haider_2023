package org.example.server.handler.card;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.server.handler.Handler;
import org.example.server.util.Request;
import org.example.server.util.Response;

import java.io.BufferedReader;

public class CardGetHandler implements Handler {
    @Override
    public Response handle(Request request, BufferedReader bufferedReader) throws JsonProcessingException {
        return null;
    }
}
