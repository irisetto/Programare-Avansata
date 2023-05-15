package org.example;

public class Game {
    private Player player1 = null;
    private Player player2 = null;

    private Board board;
    private Player currentPlayer;

    public Game(Player player1, Board board) {
        this.player1 = player1;

        this.board = board;
        this.currentPlayer = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public void start() {
        System.out.println("Gomoku game starting...");
        board.initialize();
    }
    public void switchTurn() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }
public boolean win(int row,int column, char piece){
    if (board.countConsecutivePieces(row, column, 0, 1, piece) >= 5) return true;

    // Check for five pieces in a row vertically
    if (board.countConsecutivePieces(row, column, 1, 0, piece) >= 5) return true;

    // Check for five pieces in a row diagonally (left to right)
    if (board.countConsecutivePieces(row, column, 1, 1, piece) >= 5) return true;

    // Check for five pieces in a row diagonally (right to left)
    if (board.countConsecutivePieces(row, column, 1, -1, piece) >= 5) return true;

    return false;
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

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
