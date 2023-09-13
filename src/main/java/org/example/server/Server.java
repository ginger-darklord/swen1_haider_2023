package org.example.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static final int PORT = 5934;

    public static void main(String args[]) throws IOException {
        //System.out.println("Start server...");
        ServerSocket serverSocket = new ServerSocket(PORT);
        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(() ->{
                try {
                    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintStream output = new PrintStream(socket.getOutputStream());
                    while (true) {
                        String line = input.readLine();
                        if (line == null || line.equalsIgnoreCase("quit")) {
                            socket.close();
                            return;
                        } else {
                            System.out.println("Echoserver: echo " + line);
                            output.println(line);
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }
}
