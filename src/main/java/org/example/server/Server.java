package org.example.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static final int PORT = 5934;

    public static void main(String args[]) {
        ServerSocket serverSocket = null;
        Socket socket = null;

        System.out.println("Start server...");
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Server error");
        }

        while (true) {
            try {
                socket = serverSocket.accept();
                System.out.println("Connection established");
            }catch (IOException e) {
                System.err.println("I/O error: " + e);
            }
            //new Thread for a server
            new ServerThread(socket).start();
        }
    }
}
