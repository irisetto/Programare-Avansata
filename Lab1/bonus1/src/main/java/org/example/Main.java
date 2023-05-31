package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Main {

    public static void main(String args[]) {
        //firstBulletBonus();
        secondBulletBonus(8,4);
        }

    private static void secondBulletBonus(int n, int degree) {
        int[][] adjacencyMatrix = createRegularGraph(n, degree);

        if (adjacencyMatrix != null) {
            System.out.println("Matricea de adiacenta a grafului:");
            printMatrix(adjacencyMatrix);
        }
    }
    public static int[][] createRegularGraph(int n, int degree) {
        if (n * degree % 2 != 0) {
            System.out.println("Combinatie gresita pentru un graf regulat.");
            return null;
        }

        int[][] adjacencyMatrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    adjacencyMatrix[i][j] = 0;
                }
            }
        }

        int[] degrees = new int[n];

        for (int i = 0; i < degree; i++) {
            for (int j = 0; j < n; j++) {
                if (degrees[j] < degree) {
                    int k = (j + i + 1) % n;
                    while (degrees[k] >= degree || adjacencyMatrix[j][k] != 0) {
                        k = (k + 1) % n;
                    }
                    adjacencyMatrix[j][k] = 1;
                    adjacencyMatrix[k][j] = 1;
                    degrees[j]++;
                    degrees[k]++;
                }
            }
        }

        return adjacencyMatrix;
    }
    public static int findNeighbor(int vertex, int[] degrees, int[][] adjacencyMatrix) {
        int n = degrees.length;
        List<Integer> possibleNeighbors = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (degrees[i] > 0 && i != vertex && adjacencyMatrix[vertex][i] == 0) {
                possibleNeighbors.add(i);
            }
        }

        if (possibleNeighbors.isEmpty()) {
            return -1;
        }

        Random rand = new Random();
        int randomIndex = rand.nextInt(possibleNeighbors.size());
        return possibleNeighbors.get(randomIndex);
    }
    public static void firstBulletBonus(){
        int n = 10;

        int[][] adjacencyMatrix = createCycleGraph(n);

        System.out.println("Matrice de adiacenta (A):");
        printMatrix(adjacencyMatrix);

        System.out.println("\n");
        System.out.println("Puterile matricei A:");
        System.out.println();
        for (int i = 2; i <= n; i++) {
            int[][] powerMatrix = powerMatrix(adjacencyMatrix, i);

            System.out.println("A^" + i + ":");
            printMatrix(powerMatrix);
            System.out.println();
    }
    }

        public static int[][] createCycleGraph(int n) {
            int[][] adjacencyMatrix = new int[n][n];

            for (int i = 0; i < n; i++) {
                adjacencyMatrix[i][(i - 1 + n) % n] = 1;
                adjacencyMatrix[i][(i + 1) % n] = 1;
            }

            return adjacencyMatrix;
        }
        public static int[][] powerMatrix(int[][] matrix, int power) {
            int[][] result = matrix;
            int[][] temp;

            for (int i = 2; i <= power; i++) {
                temp = multiplyMatrices(result, matrix);
                result = temp;
            }

            return result;
        }
        public static int[][] multiplyMatrices(int[][] matrix1, int[][] matrix2) {
            int n = matrix1.length;
            int[][] result = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < n; k++) {
                        result[i][j] += matrix1[i][k] * matrix2[k][j];
                    }
                }
            }

            return result;
        }
        public static void printMatrix(int[][] matrix) {
            int n = matrix.length;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
        }

    }