package org.example.server;

import com.fasterxml.jackson.databind.ObjectMapper;

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
            String requestLine = reader.readLine();

            String[] requestParts = requestLine.split(" ");
            String method = requestParts[0];
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

                }
            } else if (method.equals("PUT")) {

            } else if (method.equals("DELETE")) {

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

