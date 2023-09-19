package org.example.server;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;

//need runnable for the threads
public class RequestHandler implements Runnable{
    private Socket clientsocket;
    private HashMap<String, Handler> handler;
    public RequestHandler(Socket clientSocket) {
        this.clientsocket = clientSocket;
        this.handler = new HashMap<String, Handler>();
    }

    public void on(String path, Handler handler) {
        //depends on what the request is sends me to usershandler or packagehanlder usw...
    }

    @Override
    public void run() {
        try {
            //reads client request
            Request request = null;
            //request as parameter for the handle-method oder usershandler
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
            String requestLine = reader.readLine();

            String[] requestParts = requestLine.split(" ");
            String method = requestParts[0];

            Handler h = handler.get(requestParts[1]);
            if (h != null) {
                h.handle(request);
            }

            if (method.equals("GET")) {
                //database things ig?
            } else if (method.equals("POST")) {
                String contentType = reader.readLine();
                if(contentType != null && contentType.startsWith("Content-Type: application/json")) {
                    //reads json data and saves it in a Stringbuilder(sequence of characters)//
                    StringBuilder requestBody = new StringBuilder();
                    String line = reader.readLine();
                    while (line != null) {
                        requestBody.append(line);
                    }

                    //parse json data using jackson//
                    ObjectMapper objectMapper = new ObjectMapper();
                    //then send data to responseHandler

                }
            } else if (method.equals("PUT")) {
                //put request
            } else if (method.equals("DELETE")) {
                //delete request
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

