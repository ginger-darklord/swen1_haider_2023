package org.example.server.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.server.Request;
import org.example.server.Response;

public interface Handler {
    public Response handle(Request request) throws JsonProcessingException;
}
