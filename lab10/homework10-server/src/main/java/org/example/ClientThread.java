package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

public class ClientThread extends Thread implements Runnable {
    private static Game game;
    private static boolean gameCreated = false;
    private static boolean player1Turn;
    private static boolean gameStarted = false;
    private static boolean existWinner = false;
    private final Socket socket;
    private final GameServer gameServer;

    public ClientThread(Socket socket, GameServer gameServer) {
        this.socket = socket;
        this.gameServer = gameServer;
        player1Turn = true; //player 1 incepe
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            out.println("These are the accepted commands: \n- create game\n- join game\n- exit\n- stop\n- submit move: row column");
            String request;
            while ((request = in.readLine()) != null) {
                if (request.equals("stop")) {
                    socket.close();
                    gameServer.stop();
                    out.println("Server stopped");
                    break;
                }
                else
                if (request.equals("exit")) {
                    System.out.println("Client disconnected");
                    gameServer.removeClient(this);
                    socket.close();
                    break;
                }
                else
                if (request.equals("create game")) {
                    if (!gameCreated)
                        synchronized (gameServer) {

                            Board board = new Board();
                            Player player1 = new Player("player1", socket.getPort());
                            System.out.println(player1);
                            out.println("Created game successfully! Wait for someone to join it.");
                            game = new Game(player1, board);
                            gameCreated = true;

                        }
                    else out.println("The game is created! You can join the game.");
                }
                else
                if (request.equals("join game")) {
                    if (gameCreated){
                        synchronized (gameServer) {
                            Player player2 = new Player("player2", socket.getPort());
                            game.setPlayer2(player2);
                            System.out.println(player2);
                            out.println("Joined game successfully!");
                            gameServer.notifyClients("The game starts!! ");
                            game.start();
                            gameStarted = true;
                        }
                }
                    else out.println("The game isn't created for you to join it. Create a game!");
                }
                else
                if (request.startsWith("submit move:")) {
                    if (gameStarted) {
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
                        if (player1Turn && game.getCurrentPlayer().getPort() == socket.getPort()) {

                            if (game.getPlayer1Time() <= 0) {
                                gameServer.notifyClients("Player 2 wins! Player 1's time ran out.");
                                socket.close();
                                gameServer.stop();
                                break;
                            }
                            if (game.getBoard().isValidMove(row, column)) {
                                game.getBoard().placePiece(row, column, 'X');

                                game.getBoard().printBoard();
                                if (game.getBoard().win('X')) {
                                    synchronized (gameServer) {
                                        existWinner = true;
                                        game.setWinner(game.getCurrentPlayer());
                                    }
                                    break;
                                } else {
                                    out.println("Piece placed successfully");
                                    long elapsedTime = System.currentTimeMillis() - game.getGameStartTime();
                                    game.setPlayer1Time(game.getPlayer1Time() - elapsedTime);
                                    player1Turn = false;
                                    game.switchTurn();
                                }
                            } else if (game.getBoard().isBoardFull()) {
                                out.println("The board is full. No one wins!");
                                socket.close();
                                gameServer.stop();
                                break;
                            }
                            else
                                out.println("Not a valid move.");

                        } else if (!player1Turn && game.getCurrentPlayer().getPort() == socket.getPort()) {
                            if (game.getPlayer2Time() <= 0) {
                                gameServer.notifyClients("Player 1 wins! Player 2's time ran out.");
                                socket.close();
                                gameServer.stop();
                                break;
                            }
                            if (game.getBoard().isValidMove(row, column)) {
                                game.getBoard().placePiece(row, column, 'O');

                                game.getBoard().printBoard();
                                if (game.getBoard().win('O')) {
                                    synchronized (gameServer) {
                                        existWinner = true;
                                        game.setWinner(game.getCurrentPlayer());
                                    }
                                    break;
                                } else {
                                    out.println("Piece placed successfully");
                                    long elapsedTime = System.currentTimeMillis() - game.getGameStartTime();
                                    game.setPlayer2Time(game.getPlayer2Time() - elapsedTime);
                                    player1Turn = true;
                                    game.switchTurn();
                                }
                            } else if (game.getBoard().isBoardFull()) {
                                out.println("The board is full. No one wins!");
                                socket.close();
                                gameServer.stop();
                                break;
                            }
                            else out.println("Not a valid move.");

                        } else {
                            out.println("It's not your turn to make a move.");
                        }
                    } else out.println("The game didn't start. You can't submit move.");
                }
                else
                    out.println("Not an existing command! ");
                System.out.println("Server received the request: " + request + " from client " + socket.getPort());


            }
            if (existWinner) {
                gameServer.notifyClients(game.getCurrentPlayer().getName() + " wins!");
                socket.close();
                gameServer.stop();
            }

        } catch (IOException e) {

            System.out.println("Client disconnected unexpectedly");
            gameServer.removeClient(this);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }
}
