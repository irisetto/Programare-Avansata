package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class GameClient {
       private String serverAddress = "127.0.0.1";
       private int PORT = 8100; // The server's port
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public GameClient() throws IOException {

        socket = new Socket(serverAddress, PORT);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }
    public void run(){

        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String request;

                while ((request = reader.readLine()) != null) {
                    out.println(request);
                    if (request.equals("exit")) {
                        socket.close();
                        break;
                    }
                    else if(request.equals("stop")) {
                        System.out.println("Server stopped");
                        break;
                    }
                        String response2 = in.readLine();
                    System.out.println("Server response: " + response2);


                }

               socket.close();
            in.close();
            out.close();
        }
        catch (UnknownHostException e) {
            System.err.println("No server listening... " + e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    }

