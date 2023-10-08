package org.example.server.handler.user;

import org.example.server.StatusCode;
import org.example.server.util.Request;
import org.example.server.util.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;
class UserPostHandlerTest {
    private UserPostHandler userPostHandler;

    @BeforeEach
    public void setUp() {
        this.userPostHandler = new UserPostHandler();
    }

    @Test
    public void testValidRequest() throws IOException {
        Request mockRequest = new Request();
        mockRequest.setMethod("POST");
        mockRequest.setContentType("application/json");
        mockRequest.setBody("{}");

        Response response = userPostHandler.handle(mockRequest, new BufferedReader(new StringReader(" ")));
        assertEquals(StatusCode.OK.code, response.getStatus());
    }

    @Test
    public void testInvalidRequest() throws IOException {
        Request mockRequest = new Request();
        mockRequest.setMethod("GET");
        mockRequest.setContentType("application/json");
        mockRequest.setBody("{}");

        Response response = userPostHandler.handle(mockRequest, new BufferedReader(new StringReader(" ")));
        assertEquals(StatusCode.METHOD_NOT_ALLOWED.code, response.getStatus());
    }
  
}