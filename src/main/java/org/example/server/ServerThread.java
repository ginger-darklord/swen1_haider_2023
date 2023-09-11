package org.example.server;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    protected Socket socket;

    public ServerThread(Socket clientSocket) {
        this.socket = clientSocket;
    }

    public void run() {
        InputStream input = null;
        BufferedReader buffer = null;
        DataOutputStream output = null;
        try {
            input = socket.getInputStream();
            buffer = new BufferedReader(new InputStreamReader(input));
            output = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            return;
        }

        String line;
        while (true) {
            try {
                line = buffer.readLine();
                if (line == null || line.equalsIgnoreCase("quit")) {
                    socket.close();
                    return;
                } else {
                    //writes the output
                    output.writeBytes(line + "\n\r");
                    output.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }

    }
}
