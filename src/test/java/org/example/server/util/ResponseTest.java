package org.example.server.util;

import org.example.server.StatusCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseTest {

    @Test
    public void testResponseProperties() {
        Response response = new Response();

        response.setStatusCode(StatusCode.OK);
        response.setContentType("plain/html");
        response.setBody("Hello World");

        assertEquals(StatusCode.OK.code, response.getStatus());
        assertEquals(StatusCode.OK.message,  response.getMessage());
        assertEquals("plain/html", response.getContentType());
        assertEquals("Hello World", response.getBody());
    }

}