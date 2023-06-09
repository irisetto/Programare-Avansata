package com.example.clientspring;


import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class GameClient {
    private String serverUrl = "http://localhost:51275"; //url server
    private RestTemplate restTemplate;


    public GameClient() throws IOException {

        restTemplate = new RestTemplate();
    }
    public void run(){

        Thread userInputThread = new Thread(() -> {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String userInput;

            try {
                while ((userInput = reader.readLine()) != null) {

                    if (userInput.equals("exit")) {
                        break;
                    } else if (userInput.equals("stop")) {
                        System.out.println("Server stopped");
                        break;
                        
                    } else {
                        // Send user input to the server
                        String url = serverUrl + "/game";
                        restTemplate.postForObject(url, userInput, Void.class);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

// Start the user input thread
        userInputThread.start();

// Main thread for handling server responses
        String url = serverUrl + "/game";
        ResponseEntity<String> response;

        while (true) {
            response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
            String serverResponse = response.getBody();

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

                System.exit(0);
                break;
            }
        }
    }
}

