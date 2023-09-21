package org.example.server;

import org.example.server.handler.Handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketHandler implements Runnable {

    private Socket clientSocket;
    private RequestManager requestManager;

    public SocketHandler(Socket clientSocket, RequestManager requestManager) {
        this.clientSocket = clientSocket;
        this.requestManager = requestManager;
    }
    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            String requestLine = reader.readLine();
            Request request = new Request(requestLine);

            String[] requestParts = requestLine.split(" ");
            //gets the method and saves it in request instance
            String method = requestParts[0];
            request.setMethod(method);

            String path = requestParts[1];
            request.setPath(path);
            Handler h = this.requestManager.getHandler(path);
            if (h != null) {
                h.handle(request);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
