package org.example.server.handler.user;

import org.example.application.models.User;
import org.example.application.repository.UserRepository;
import org.example.server.util.Request;
import org.example.server.util.Response;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UserPutHandlerTest {
    @Test
    public void testHandle() throws IOException {
        User user = new User("altenhof", "markus", "Authorization: Bearer altenhof-mtcgToken", 20);
        UserRepository userRepository = new UserRepository();
        userRepository.createUser(user);
        Request request = new Request();
        request.setMethod("PUT");
        request.setPath("/users/altenhof");
        request.setContentType("Content-Type: application/json");
        request.setAuthorization("Authorization: Bearer altenhof-mtcgToken");
        request.setBody("{\"Name\": \"Altenhofer\", \"Bio\": \"me codin...\",  \"Image\": \":-D\"}");
        UserPutHandler userPutHandler = new UserPutHandler();

        Response response = userPutHandler.handle(request);

        assertEquals("OK", response.getMessage());
        assertEquals(200, response.getStatus());
    }

}