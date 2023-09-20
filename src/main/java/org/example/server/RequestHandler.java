package org.example.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.server.handler.Handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;

//need runnable for the threads
public class RequestHandler implements Runnable{
    private Socket clientsocket;
    private HashMap<String, Handler> handlers;
    public RequestHandler(Socket clientSocket) {
        this.clientsocket = clientSocket;
        this.handlers = new HashMap<String, Handler>();
    }

    public void on(String path, Handler handler) {
       this.handlers.put(path, handler);
    }

    @Override
    public void run() {
        try {
            Request request = new Request();
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
            String requestLine = reader.readLine();
            request.setRequest(requestLine);

            String[] requestParts = requestLine.split(" ");
            //gets the method and saves it in request instance
            String method = requestParts[0];
            request.setMethod(method);

            String path = requestParts[1];
            request.setPath(path);
            Handler h = handlers.get(path);
            if (h != null) {
                h.handle(request);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

