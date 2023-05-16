package org.example;

import static org.example.Board.SIZE;

public class Game {
    private Player player1 = null;
    private Player player2 = null;
    private Player winner;
    private Board board;
    private Player currentPlayer;
    private long player1Time; //timpul ramas al player1
    private long player2Time; //timpul ramas al player2
    private long gameStartTime;  //timpul de start al jocului
    private long timeLimit; //timpul limita

    public Game(Player player1, Board board) {
        this.player1 = player1;
        this.timeLimit = 300000;
        this.player1Time = timeLimit;
        this.player2Time = timeLimit;
        this.board = board;
        this.currentPlayer = player1;
    }

    public void start() {
        System.out.println("Gomoku game starting...");
        this.gameStartTime = System.currentTimeMillis();
        board.initialize();
    }

    public void switchTurn() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public long getPlayer1Time() {
        return player1Time;
    }

    public void setPlayer1Time(long player1Time) {
        this.player1Time = player1Time;
    }

    public long getPlayer2Time() {
        return player2Time;
    }

    public void setPlayer2Time(long player2Time) {
        this.player2Time = player2Time;
    }

    public long getGameStartTime() {
        return gameStartTime;
    }

    public void setGameStartTime(long gameStartTime) {
        this.gameStartTime = gameStartTime;
    }

    public long getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(long timeLimit) {
        this.timeLimit = timeLimit;
    }
}
