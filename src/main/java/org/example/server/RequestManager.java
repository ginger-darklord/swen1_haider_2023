package org.example.server;

import org.example.server.handler.Handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;

//need runnable for the threads
public class RequestManager{
    private HashMap<String, Handler> handlers;
    public RequestManager() {
        this.handlers = new HashMap<String, Handler>();
    }

    public void on(String path, Handler.HttpMethod method, Handler handler) {

        this.handlers.put(path, handler);
    }

    public Handler getHandler(String path) {
        return this.handlers.get(path);
    }

}

