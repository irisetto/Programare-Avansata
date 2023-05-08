package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread implements Runnable{
    private Socket socket ;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try( BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

             PrintWriter out = new PrintWriter(socket.getOutputStream(),true);)
            { String request ;
            while ((request = in.readLine()) != null) {
                if (request.equals("stop")) {

                    break;
                }
                    System.out.println("Server received the request: " + request);
                    out.println("Server received the request: " + request);

            }
            out.println("Server stopped");
            // Close the socket and streams

            System.out.println("Client disconnected");
                GameServer.stop();
        } catch (IOException e) {
            System.err.println("Communication error... " + e);
        } finally {
            try {
                System.out.println("close");
                socket.close();
            } catch (IOException e) { System.err.println (e); }
        }
    }
}
