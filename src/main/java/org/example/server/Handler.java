package org.example.server;

public interface Handler {
    public Response handle(Request request);
}
