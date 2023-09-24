package org.example.server.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.server.Request;
import org.example.server.Response;

public interface Handler {

    public enum HttpMethod{
        POST("POST"),
        GET("GET"),
        PUT("PUT"),
        DELETE("DELETE");
        ;

        private String method;
        HttpMethod(String method) {
            this.method = method;
        }

    }
    public Response handle(Request request) throws JsonProcessingException;
}
