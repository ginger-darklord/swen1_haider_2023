package org.example.application;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Game {
    public void connection() throws IOException {
        Socket clientSocket = new Socket();
        clientSocket.connect(new InetSocketAddress("www.technikum-wien.at", 80));

        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        output.write("GET / HTTP/1.1\r\n");
        output.write("\r\n");
        output.flush();

        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String line = null;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
    }
}
