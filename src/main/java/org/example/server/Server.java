package org.example.server;

import org.example.server.handler.*;
import org.example.server.handler.battle.BattlePostHandler;
import org.example.server.handler.card.CardGetHandler;
import org.example.server.handler.deck.DeckGetHandler;
import org.example.server.handler.deck.DeckPutHandler;
import org.example.server.handler.packages.PackagePostHandler;
import org.example.server.handler.scoreboard.ScoreboardGetHandler;
import org.example.server.handler.session.SessionPostHandler;
import org.example.server.handler.stats.StatsGetHandler;
import org.example.server.handler.trading.TradingDeleteHandler;
import org.example.server.handler.trading.TradingGetHandler;
import org.example.server.handler.trading.TradingPostHandler;
import org.example.server.handler.transaction.TransactionPostHandler;
import org.example.server.handler.user.UserGetHandler;
import org.example.server.handler.user.UserPostHandler;
import org.example.server.handler.user.UserPutHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private int PORT = 10001;
    private ServerSocket serverSocket;
    private RequestManager requestManager;
    private ArrayList<Thread> clientThreads = new ArrayList<>();
    private volatile boolean isRunning = false;

    public Server() {
        requestManager = new RequestManager();
        requestManager.on("/users", Handler.HttpMethod.POST, new UserPostHandler());
        requestManager.on("/users/kienboec", Handler.HttpMethod.GET, new UserGetHandler());
        requestManager.on("/users/altenhof", Handler.HttpMethod.GET, new UserGetHandler());
        requestManager.on("/users/someGuy", Handler.HttpMethod.GET, new UserGetHandler());
        requestManager.on("/users/kienboec", Handler.HttpMethod.PUT, new UserPutHandler());
        requestManager.on("/users/altenhof", Handler.HttpMethod.PUT, new UserPutHandler());
        requestManager.on("/sessions", Handler.HttpMethod.POST, new SessionPostHandler());
        requestManager.on("/packages", Handler.HttpMethod.POST, new PackagePostHandler());
        requestManager.on("/cards", Handler.HttpMethod.GET, new CardGetHandler());
        requestManager.on("/deck", Handler.HttpMethod.GET, new DeckGetHandler());
        requestManager.on("/deck?format=plain", Handler.HttpMethod.GET, new DeckGetHandler());
        requestManager.on("/deck", Handler.HttpMethod.PUT, new DeckPutHandler());
        requestManager.on("/tradings", Handler.HttpMethod.DELETE, new TradingDeleteHandler());
        requestManager.on("/tradings", Handler.HttpMethod.GET, new TradingGetHandler());
        requestManager.on("/tradings", Handler.HttpMethod.POST, new TradingPostHandler());
        requestManager.on("/transactions/packages", Handler.HttpMethod.POST, new TransactionPostHandler());
        requestManager.on("/stats", Handler.HttpMethod.GET, new StatsGetHandler());
        requestManager.on("/scoreboard", Handler.HttpMethod.GET, new ScoreboardGetHandler());
        requestManager.on("/battles", Handler.HttpMethod.POST, new BattlePostHandler());
    }


    public void start() throws IOException {
        System.out.println("Server start");
        serverSocket = new ServerSocket(this.PORT);
        System.out.println("Server is listening on Port " + PORT);

        this.isRunning = true;

        while (isRunning) {
            Socket clientSocket = serverSocket.accept();
            Thread thread = new Thread(new SocketHandler(clientSocket, requestManager));
            thread.start();

            clientThreads.add(thread);
        }
    }

    public void stop() {
        this.isRunning = false;
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }

            for (Thread thread : clientThreads) {
                thread.interrupt();
                thread.join();
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    //server maybe auf einen docker container starten
}
