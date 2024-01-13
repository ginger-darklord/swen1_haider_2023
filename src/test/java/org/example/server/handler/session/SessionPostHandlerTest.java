package org.example.server.handler.session;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.application.models.User;
import org.example.application.repository.UserRepository;
import org.example.server.util.Request;
import org.example.server.util.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SessionPostHandlerTest {
    private SessionPostHandler sessionPostHandler = new SessionPostHandler();

    @Test
    public void testHandle() throws JsonProcessingException {
        //so its already in db wenn session under way
        User user = new User("doe", "jane", "doe-token", 20);
        UserRepository userRepository = new UserRepository();
        userRepository.createUser(user);

        Request request = new Request();
        request.setMethod("POST");
        request.setPath("/sessions");
        request.setContentType("Content-Type: application/json");
        request.setBody("{\"Username\":\"doe\", \"Password\":\"jane\"}");

        Response result = sessionPostHandler.handle(request);

        assertEquals("OK", result.getMessage());
        assertEquals(200, result.getStatus());
    }

}