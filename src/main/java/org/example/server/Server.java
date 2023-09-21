package org.example.server;

import org.example.application.Game;
import org.example.server.handler.Handler;
import org.example.server.handler.UserPostHandler;
import org.example.server.handler.UsersGetHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int PORT = 10001;
    private ServerSocket serverSocket;
    private Game game;
    private RequestManager requestManager;

    public Server() {
        requestManager = new RequestManager();
        requestManager.on("/users", "POST", new UserPostHandler()); //Handler.POST so i know that its user and a post
        requestManager.on("/users", new UsersGetHandler());
    }

    public void start() throws IOException {
        System.out.println("Server start");
        serverSocket = new ServerSocket(this.PORT);
        System.out.println("Server is listening on Port " + PORT);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            Thread thread = new Thread(new SocketHandler(clientSocket, requestManager));
            thread.start();
        }
    }
}
