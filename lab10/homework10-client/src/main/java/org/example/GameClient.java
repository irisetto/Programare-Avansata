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

            Thread userInputThread = new Thread(() -> {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String userInput;

                try {
                    while ((userInput = reader.readLine()) != null) {
                        out.println(userInput);
                        if (userInput.equals("exit")) {
                            socket.close();
                            break;
                        } else if (userInput.equals("stop")) {
                            System.out.println("Server stopped");
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

// Start the user input thread
            userInputThread.start();

// Main thread for handling server responses
            try {
                String serverResponse;

                while ((serverResponse = in.readLine()) != null) {

                    if (serverResponse.isEmpty()) {

                        continue;
                    }
                    if (serverResponse.startsWith("These are the accepted commands")) {
                        System.out.println(serverResponse);
                    } else if(serverResponse.startsWith("- "))
                        System.out.println(serverResponse);
                    else {
                        System.out.println("Server response: " + serverResponse);
                    }
                    if (serverResponse.contains("wins")) {

                        socket.close();
                        System.exit(0);
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
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

