package org.example.server;

import org.example.server.handler.Handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;

public class RequestManager{
    private HashMap<String, Handler> handlers;
    public RequestManager() {
        this.handlers = new HashMap<String, Handler>();
    }

    public void on(String path, Handler.HttpMethod method, Handler handler) {
        String key = path + " " + method.name();
        this.handlers.put(key, handler);
    }

    public Handler getHandler(String path, Handler.HttpMethod method) {
        String key = path + " " + method.name();
        return this.handlers.get(key);
    }

}

