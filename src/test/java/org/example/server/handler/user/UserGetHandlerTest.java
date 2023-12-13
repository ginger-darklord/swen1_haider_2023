package org.example.server.handler.user;

import org.example.application.models.User;
import org.example.server.StatusCode;
import org.example.server.util.Request;
import org.example.server.util.Response;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UserGetHandlerTest {
    private UserGetHandler userGetHandler;

    @BeforeEach
    public void setUp() {
        this.userGetHandler = new UserGetHandler();
    }

    public void testValidRequest() throws IOException {
        Request mockRequest = new Request();
        mockRequest.setMethod("GET");
        mockRequest.setContentType("Content-Type: application/json");
        mockRequest.setBody("{}");

        Response response = userGetHandler.handle(mockRequest);
        assertEquals(StatusCode.OK.code, response.getStatus());
    }

}