package org.example.server;

import org.example.application.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int PORT = 10001;
    private ServerSocket serverSocket;
    private Game game;

    public Server(Game game) {
        this.game = game;
    }

    public void start() throws IOException {
        System.out.println("Server start");
        serverSocket = new ServerSocket(this.PORT);
        System.out.println("Server is listening on Port " + PORT);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            Thread thread = new Thread(new RequestHandler(clientSocket));

        }
    }
}
