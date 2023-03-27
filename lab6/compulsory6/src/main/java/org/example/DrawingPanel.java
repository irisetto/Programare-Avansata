package org.example;

import javax.swing.*;
import java.awt.*;

public class DrawingPanel extends JPanel {
    final MainFrame frame;
    final static int W = 800, H = 600;
    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        initPanel();
    }
    private void initPanel() {
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
    }
    @Override
    protected void paintComponent(Graphics g) {
//get the number of dots (numVertices)
//get the probability that two dots form a line (edgeProbability)
//draw the board, that is:
//compute the coordinates of the dots
//draw the dots
//draw the lines
    }
}