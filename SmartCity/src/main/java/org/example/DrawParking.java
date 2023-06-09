package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class DrawParking extends JPanel {
    private int capacity;
    private int occupied;
    int[] occupiedIndices = new int[occupied];
    public DrawParking(int capacity, int occupied) {
        this.capacity = capacity;
        this.occupied = occupied;
        setPreferredSize(new Dimension(650, 400));
        generateOccupiedIndices();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int squareSize = calculateSquareSize(getWidth(), getHeight());
        int rows = (int) Math.ceil((double) capacity / 10);
        int columns = (int) Math.ceil((double) capacity / (rows ));

        int x = 0;
        int y = 0;

        g.setColor(Color.BLACK);

        for (int i = 0; i < capacity; i++) {
            if (isOccupied(occupiedIndices, i)) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.GREEN);
            }

            g.fillRect(x, y, squareSize, squareSize);

            g.setColor(Color.BLACK);
            g.drawRect(x, y, squareSize, squareSize);

            // Draw number in the middle of the square
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 12));
            String number = String.valueOf(i + 1);
            int stringWidth = g.getFontMetrics().stringWidth(number);
            int stringHeight = g.getFontMetrics().getHeight();
            int centerX = x + squareSize / 2 - stringWidth / 2;
            int centerY = y + squareSize / 2 + stringHeight / 2;
            g.drawString(number, centerX, centerY);

            x += squareSize;
            if (x >= squareSize * columns) {
                x = 0;
                y += squareSize;
            }
        }
    }

    private boolean isOccupied(int[] occupiedIndices, int index) {
        for (int i : occupiedIndices) {
            if (i == index) {
                return true;
            }
        }
        return false;
    }

    private void generateOccupiedIndices() {
        occupiedIndices = new int[occupied];
        Random random = new Random();

        // Generate random occupied square indices
        for (int i = 0; i < occupied; i++) {
            int randomIndex = random.nextInt(capacity);
            while (isOccupied(occupiedIndices, randomIndex)) {
                randomIndex = random.nextInt(capacity);
            }
            occupiedIndices[i] = randomIndex;
        }
    }

    private int calculateSquareSize(int width, int height) {
        int maxSquaresPerRow = (int) Math.ceil(Math.sqrt(capacity));
        int maxSquaresPerColumn = (int) Math.ceil((double) capacity / maxSquaresPerRow);

        int squareSize = Math.min(width / maxSquaresPerRow, height / (maxSquaresPerColumn));
        return squareSize;
    }
}
