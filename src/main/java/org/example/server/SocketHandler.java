package org.example.server;

import org.example.server.handler.Handler;
import org.example.server.util.Request;
import org.example.server.util.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

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
            //gets the method
            String method = requestParts[0];
            request.setMethod(method);
            System.out.println("Request Method: " + request.getMethod());

            // Parse and store headers in the request object
            Map<String, String> headers = new HashMap<>();
            String line;
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                String[] headerParts = line.split(": ");
                if (headerParts.length == 2) {
                    String headerName = headerParts[0];
                    String headerValue = headerParts[1];
                    //System.out.println("Header content: " + headerName + " " + headerValue);
                    headers.put(headerName, headerValue);
                }
            }

            // Set the headers in the request object
            request.setHeaders(headers);


            if(requestParts.length >= 2) {
                String path = requestParts[1];
                request.setPath(path);

                Handler h = this.requestManager.getHandler(path, Handler.HttpMethod.valueOf(method));
                if (h != null) {
                    Response handled = h.handle(request, reader);
                    System.out.println("Response: " + handled.getStatus());
                }
            } else {
                throw new ConnectException();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
