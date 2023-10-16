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
            InputStreamReader isr = new InputStreamReader(this.clientSocket.getInputStream());
            BufferedReader reader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            while (reader.ready()) {
                sb.append((char)reader.read());
            }
            String[] rps = sb.toString().split("\r\n\r\n");
            String header = rps[0];
            String body = rps[1];

            //parsing path, method, usw.. of request header
            Request request = new Request();
            request.parseRequest(header);
            request.setBody(body);

            Handler h = this.requestManager.getHandler(request.getPath(), Handler.HttpMethod.valueOf(request.getMethod()));
            if (h != null) {
                Response handled = h.handle(request);
                handled.printResponse();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
