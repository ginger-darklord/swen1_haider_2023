package org.example;

import org.example.application.Game;
import org.example.server.Server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Server server = new Server();

        try {
            server.start();
            //server.on("/users", new UsersHandler());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
