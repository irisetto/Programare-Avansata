package org.example;

public class Board {

    public static final int SIZE = 15;
    private char[][] board;


    public Board() {
        board = new char[SIZE][SIZE];
    }

    public void initialize() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                board[i][j] = '#';
    }

    public boolean isCellEmpty(int row, int column) {
        return board[row][column] == '#';
    }

    public void placePiece(int row, int column, char piece) {
        board[row][column] = piece;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (board[i][j] == '#')
                    return false;
        return true;
    }

    public void printBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean isValidMove(int row, int column) {

        return isValidPosition(row, column) && isCellEmpty(row, column);
    }


    private boolean isValidPosition(int row, int column) {
        return row >= 0 && row < SIZE && column >= 0 && column < SIZE;
    }

    public boolean win(char piece) {
        //ORIZONTAL
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col <= SIZE - 5; col++) {
                if (board[row][col] == piece && board[row][col + 1] == piece &&
                        board[row][col + 2] == piece && board[row][col + 3] == piece &&
                        board[row][col + 4] == piece) {
                    return true;
                }
            }
        }
        // VERTICAL
        for (int col = 0; col < SIZE; col++) {
            for (int row = 0; row <= SIZE - 5; row++) {
                if (board[row][col] == piece && board[row + 1][col] == piece &&
                        board[row + 2][col] == piece && board[row + 3][col] == piece &&
                        board[row + 4][col] == piece) {
                    return true;
                }
            }
        }

        // DIAGONALA DESCENDENTA
        for (int row = 0; row <= SIZE - 5; row++) {
            for (int col = 0; col <= SIZE - 5; col++) {
                if (board[row][col] == piece && board[row + 1][col + 1] == piece &&
                        board[row + 2][col + 2] == piece && board[row + 3][col + 3] == piece &&
                        board[row + 4][col + 4] == piece) {
                    return true;
                }
            }
        }

        // DIAGONALA DESCENDENTA INVERSA
        for (int row = 4; row < SIZE; row++) {
            for (int col = 0; col <= SIZE - 5; col++) {
                if (board[row][col] == piece && board[row - 1][col + 1] == piece &&
                        board[row - 2][col + 2] == piece && board[row - 3][col + 3] == piece &&
                        board[row - 4][col + 4] == piece) {
                    return true;
                }
            }
        }
        return false;
    }
}
