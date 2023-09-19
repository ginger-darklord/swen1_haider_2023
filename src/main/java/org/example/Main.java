package org.example;

import org.example.application.Game;
import org.example.server.Server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Server server = new Server(new Game());
        //server.on("/users", new UsersHandler());

        try {
            server.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
