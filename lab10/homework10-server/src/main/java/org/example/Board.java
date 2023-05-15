package org.example;

public class Board {

        public static final int SIZE = 15;
        private char[][] board;


        public Board() {
            board = new char[SIZE][SIZE];
        }

    public void initialize() {
            for(int i = 0;i<SIZE;i++)
                for(int j=0;j<SIZE;j++)
                    board[i][j]='#';
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
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }
    public boolean isValidMove(int row, int column) {

            return isValidPosition(row,column) && isCellEmpty(row, column);
    }

    public int countConsecutivePieces(int row, int column, int i, int j, char piece) {
        int count = 1;
        int currentRow = row + i;
        int currentColumn = column + j;

        while (isValidPosition(currentRow, currentColumn) && board[currentRow][currentColumn] == piece) {
            count++;
            currentRow += i;
            currentColumn += j;
        }

        return count;  
    }

    private boolean isValidPosition(int row, int column) {
        return row >= 0 && row < SIZE && column >= 0 && column < SIZE;
        }
}
