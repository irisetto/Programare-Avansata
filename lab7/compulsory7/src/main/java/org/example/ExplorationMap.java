package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExplorationMap {
    private final Cell[][] matrix;
    int n;
    int row2,col2;
    //Cell should be a wrapper or alias for List<Token>
    public ExplorationMap(int n) {
        matrix = new Cell[n][n];
        this.n = n;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = new Cell();
            }
        }
    }
    public boolean visit(int row, int col, Robot robot) {
        synchronized (matrix[row][col]) {
            if(!matrix[row][col].isVisited()){
//            if the cell is not visited {
//                the robot extract tokens
                List<Token> tokens = robot.explore.getMem().extractTokens(matrix[row][col].getSize());
//                and store the tokens in the cell (it becomes visited)
                 matrix[row][col].storeTokens(tokens);
//                display a success message
            System.out.println(robot.getName() + " visited cell (" + row + "," + col + ") and stored " + tokens.size() + " tokens.");

            return true;
            } else  {
            System.out.println(robot.getName() + " tried to visit cell (" + row + "," + col + "), but it was already visited.");
            return false;
        }
    }
}
    public synchronized Cell getUnvisitedCell() {
        do {
            row2 = (int) (Math.random() * matrix.length);
            col2 = (int) (Math.random() * matrix[0].length);
        } while (matrix[row2][col2].isVisited());
        return matrix[row2][col2];
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                sb.append(matrix[i][j].toString()).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    public class Cell {
        private final List<Token> tokens = new ArrayList<>();
        private boolean visited = false;

        public boolean isVisited() {
            return visited;
        }
        public int getRow2() {
            return row2;
        }

        public int getCol2() {
            return col2;
        }
        public void visit(Robot robot, int howMany) {
            visited = true;
        }
        public int getSize() {
            if (visited) {
                return 0;
            } else {
                return (int) Math.floor(Math.random() * tokens.size()) + 1;
            }
        }

        public void storeTokens(List<Token> newTokens) {
            visited = true;
            tokens.addAll(newTokens);
        }
        @Override
        public String toString() {
            if (visited) {
                return "X";
            } else {
                return ".";
            }
        }
    }
}
