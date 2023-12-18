package org.example;

import org.example.server.Server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Server server = new Server();

        try {
            server.start();
            server.stop();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Delete delete = new Delete();
        //delete.deleteAllUser();
    }
}
