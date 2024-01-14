package org.example.server.handler.user;

import org.example.application.models.User;
import org.example.application.repository.UserRepository;
import org.example.server.util.Request;
import org.example.server.util.Response;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UserGetHandlerTest {
    @Test
    public void testHandle() throws IOException {
        User user = new User("kienboec", "daniel", "Authorization: Bearer kienboec-mtcgToken", 20, "having fun", ":D", "Kienboec", 100);
        UserRepository userRepository = new UserRepository();
        userRepository.createUser(user);
        Request request = new Request();
        UserGetHandler userGetHandler = new UserGetHandler();
        request.setMethod("GET");
        request.setAuthorization("Authorization: Bearer kienboec-mtcgToken");
        request.setPath("/users/kienboec");

        Response response = userGetHandler.handle(request);

        assertEquals("OK", response.getMessage());
        assertEquals(200, response.getStatus());
    }

}