package org.example.server;

import org.example.application.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int PORT = 5934;
    private ServerSocket serverSocket;
    private final Game game;

    public Server(Game game) {
        this.game = game;
    }

    private void start() throws IOException {
        System.out.println("Server start");
        serverSocket = new ServerSocket(this.PORT);
        System.out.println("Server is listening on Port " + PORT);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            Thread thread = new Thread(new RequestHandler(clientSocket));

        }
    }

    private void run() {
        while (true) {
            try {
                Socket socket = this.serverSocket.accept();
                new Thread(()-> {
                    try {
                        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintStream output = new PrintStream(socket.getOutputStream());

                        while (true) {
                            String line = input.readLine();
                            if (line == null || line.equalsIgnoreCase("quit")) {
                                socket.close();
                                return;
                            }
                            else {
                                output.println(line);
                                output.flush();
                            }
                        }
                    } catch (IOException ex) {
                        System.err.println("Echoserver: echo " + ex);
                    }
                }).start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
