package org.example.server.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestTest {
    @Test
    public void testRequestProperties() {
        Request request = new Request();

        request.setMethod("GET");
        request.setPath("/users/kienboc");
        request.setContentType("application/json");
        request.setBody("{}");

        assertEquals("GET", request.getMethod());
        assertEquals("/users/kienboc", request.getPath());
        assertEquals("application/json", request.getContentType());
        assertEquals("{}", request.getBody());
    }

}