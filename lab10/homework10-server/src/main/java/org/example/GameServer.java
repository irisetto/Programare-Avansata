package org.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GameServer {
    public final int PORT = 8100;
    private boolean isTrue=false;
    private ServerSocket serverSocket ;
    private List<ClientThread> clients;
    private boolean startedGame;
    public GameServer(){
        isTrue=true;
        this.clients = new ArrayList<>();
        this.startedGame = false;
    }

    public void removeClient(ClientThread clientThread) {
        clients.remove(clientThread);
    }

    public void run () {
        try {
            serverSocket = new ServerSocket(PORT);

            while (isTrue) {
                if (clients.size() < 2) {
                    System.out.println ("Waiting for a client ...");
                Socket socket = serverSocket.accept();
                System.out.println("Client connected: " + socket.getInetAddress().getHostName());
                // Execute the client's request in a new thread


                        ClientThread client = new ClientThread(socket, this);
                        clients.add(client);
                        client.start();
                        System.out.println("nr de clienti conectati " + clients.size());
                        if(clients.size()==2) {
                            startedGame=true;
                            System.out.println("The game can start!");
                        }
                    }
                    else {
                    if (clients.size() >= 2) {
                        Socket socket = serverSocket.accept();
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        out.println("The game is already full. Cannot accept new players.");
                        out.close();
                        socket.close();

                    }
                    }

            }
        } catch (IOException e) {
            System.err. println ("Ooops... " + e);
        } finally {
            stop();


        }
    }
    public void stop() {
        try {
            isTrue = false;
    serverSocket.close();
        System.out.println("Server stopped");
    }
catch (IOException e){
    System.out.println("bla");
}
}

    public List<ClientThread> getClients() {
        return clients;
    }

    public void setClients(List<ClientThread> clients) {
        this.clients = clients;
    }

    public boolean isStartedGame() {
        return startedGame;
    }
}
