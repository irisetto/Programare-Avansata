package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    public static final int PORT = 8100;
    private static boolean isTrue=false;
    private static ServerSocket serverSocket ;
    public GameServer(){isTrue=false;}
 public void run () {
        try {
            serverSocket = new ServerSocket(PORT);
            isTrue=true;
            while (isTrue) {
                System.out.println ("Waiting for a client ...");
                Socket socket = serverSocket.accept();
                System.out.println("Client connected: " + socket.getInetAddress().getHostName());
                // Execute the client's request in a new thread
                new ClientThread(socket).start();
            }
        } catch (IOException e) {
            System.err. println ("Ooops... " + e);
        } finally {
            stop();


        }
    }
    public static void stop() {
        try {GameServer.isTrue = false;
    GameServer.serverSocket.close();
        System.out.println("Server stopped");
    }
catch (IOException e){
    System.out.println("bla");
}
}}
