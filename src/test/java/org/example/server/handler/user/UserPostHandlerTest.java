package org.example.server.handler.user;

import org.example.server.util.Request;
import org.example.server.util.Response;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UserPostHandlerTest {
   private Request request;
   private UserPostHandler userPostHandler = new UserPostHandler();

   @Test
   public void testHandle() throws IOException {
       request = new Request();
       request.setMethod("POST");
       request.setContentType("Content-Type: application/json");
       request.setPath("/users");
       request.setBody("{\"Username\":\"dauner\", \"Password\":\"susi\"}");

       Response result = userPostHandler.handle(request);
       assertEquals("CREATED", result.getMessage());
       assertEquals(201, result.getStatus());
   }



}