package org.example.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

//need runnable for the threads
public class RequestHandler implements Runnable{
    private Socket clientsocket;
    public RequestHandler(Socket clientSocket) {
        this.clientsocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            //reads client request
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

