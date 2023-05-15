package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

public class ClientThread extends Thread implements Runnable{
    private Socket socket ;
    private GameServer gameServer;
    private static Game game;
    private static boolean gameCreated=false;
    private static boolean player1Turn;
    private static boolean gameStarted=false;
    public ClientThread(Socket socket, GameServer gameServer) {
        this.socket = socket;
        this.gameServer = gameServer;
        this.player1Turn = true; //player 1 incepe
    }

    @Override
    public void run() {
        try( BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

             PrintWriter out = new PrintWriter(socket.getOutputStream(),true);)
            {
                String request ;
            while ((request = in.readLine()) != null) {
                if (request.equals("stop")) {
                    socket.close();
                    gameServer.stop();
                    out.println("Server stopped");
                    break;
                }
                if (request.equals("exit")) {
                    System.out.println("Client disconnected");
                    gameServer.removeClient(this);
                    socket.close();
                    break;
                }
                if (request.equals("create game") ) {
                    if(gameCreated==false)
                        synchronized (gameServer) {

                        Board board = new Board();
                        Player player1 = new Player("player1", socket.getPort());
                            System.out.println(player1);
                        game = new Game(player1, board);
                        gameCreated = true;
                            out.println("Game created successfully");
                    }
                    else  out.println("The game is created! You can join the game.");
                }

                if (request.equals("join game")  )
                    if (gameCreated == true)
                        synchronized (gameServer) {
                            Player player2 = new Player("player2", socket.getPort());
                            game.setPlayer2(player2);
                            System.out.println(player2);
                            out.println("Joined the game successfully");

                            game.start();
                            gameStarted = true;
                        }
                    else out.println("The game isn't created for you to join it. Create a game!");

                   if (request.startsWith("submit move:")) {
                       if(gameStarted) {
                         String[] parts = request.split(" ");
                       if (parts.length != 4) {
                           out.println("Invalid move request");
                           continue;
                       }
                       int row, column;
                       try {
                           row = Integer.parseInt(parts[2]);
                           column = Integer.parseInt(parts[3]);
                       } catch (NumberFormatException e) {
                           out.println("Invalid move request");
                           continue;
                       }
                       if(player1Turn && game.getCurrentPlayer().getPort()==socket.getPort()){

                               if(game.getBoard().isValidMove(row,column)){
                             game.getBoard().placePiece(row,column,'X');
                             out.println("Piece placed successfully");
                             game.getBoard().printBoard();
                                   if (game.win(row, column, 'X')) {
                                       out.println("You win!!!");
                                   }
                         }
                               else out.println("Not a valid move.");
                         player1Turn=false;
                         game.switchTurn();
                       }
                    else if (!player1Turn && game.getCurrentPlayer().getPort() == socket.getPort()) {
                       if(game.getBoard().isValidMove(row,column)){
                           game.getBoard().placePiece(row,column,'O');
                           out.println("Piece placed successfully");
                           game.getBoard().printBoard();
                           if (game.win(row, column, 'O')) {
                               out.println("You win!!!");
                           }
                       }
                       else out.println("Not a valid move.");
                       player1Turn=true;
                       game.switchTurn();
                   }
                    else {
                           out.println("It's not your turn to make a move.");
                       }
                   } else out.println("The game didn't start. You can't submit move.");
               }

                System.out.println("Server received the request: " + request + " from client " + socket.getPort());



            }

        } catch (IOException e) {

            System.out.println("Client disconnected unexpectedly");
            gameServer.removeClient(this);
        } finally {
            try {
                socket.close();
            } catch (IOException e) { System.err.println (e); }
        }
    }

    public Socket getSocket() {
        return socket;
    }
}
