package org.example.server;

import org.example.server.handler.*;
import org.example.server.handler.card.CardGetHandler;
import org.example.server.handler.card.CardPutHandler;
import org.example.server.handler.deck.DeckGetHandler;
import org.example.server.handler.packages.PackagePostHandler;
import org.example.server.handler.session.SessionPostHandler;
import org.example.server.handler.trading.TradingDeleteHandler;
import org.example.server.handler.trading.TradingGetHandler;
import org.example.server.handler.trading.TradingPostHandler;
import org.example.server.handler.user.UserGetHandler;
import org.example.server.handler.user.UserPostHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int PORT = 10001;
    private ServerSocket serverSocket;
    private RequestManager requestManager;

    public Server() {
        requestManager = new RequestManager();
        requestManager.on("/users", Handler.HttpMethod.POST, new UserPostHandler()); //Handler.POST so i know that its user and a post
        requestManager.on("/users", Handler.HttpMethod.GET, new UserGetHandler());
        requestManager.on("/sessions", Handler.HttpMethod.POST, new SessionPostHandler());
        requestManager.on("/packages", Handler.HttpMethod.POST, new PackagePostHandler());
        requestManager.on("/cards", Handler.HttpMethod.GET, new CardGetHandler());
        requestManager.on("/cards", Handler.HttpMethod.PUT, new CardPutHandler());
        requestManager.on("/deck", Handler.HttpMethod.GET, new DeckGetHandler());
        requestManager.on("/deck", Handler.HttpMethod.PUT, new CardPutHandler());
        requestManager.on("/tradings", Handler.HttpMethod.DELETE, new TradingDeleteHandler());
        requestManager.on("/tradings", Handler.HttpMethod.GET, new TradingGetHandler());
        requestManager.on("/tradings", Handler.HttpMethod.POST, new TradingPostHandler());
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
    //server maybe auf einen docker container starten
}
