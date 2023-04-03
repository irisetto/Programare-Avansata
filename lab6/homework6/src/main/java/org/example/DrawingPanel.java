package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class DrawingPanel extends JPanel {
    private static final int DOT_SIZE = 20;
    final MainFrame frame;
    final static int W = 800, H = 600;
    int numD;
    float probL;
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
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, W, H);
        g2d.clearRect(0,0,W,H);
        g2d.setStroke(new BasicStroke(4));
        numD = frame.configPanel.numDots;
        probL = frame.configPanel.edgeProb;
        List<Point> vertices = new LinkedList<Point>();
        Map<Point,List<Point>> adiacentList = new HashMap<>();
        double angleIncrement = 2 * Math.PI / numD;
        double radius = H / 2 - 10;
        int centerX = W / 2;
        int centerY = H / 2;
        for (int i = 0; i < numD; i++) {
            double angle = i * angleIncrement;
            int x = (int) Math.round(centerX + radius * Math.cos(angle));
            int y = (int) Math.round(centerY + radius * Math.sin(angle));
            vertices.add(new Point(x, y));
            adiacentList.put(new Point(x,y),new LinkedList<Point>());
        }

        g2d.setColor(Color.GRAY);
        for (int i = 0; i < numD - 1; i++) {
            for (int j = i + 1; j < numD; j++) {
                if (Math.random() < probL) {
                    Point p1 = vertices.get(i);
                    Point p2 = vertices.get(j);
                    g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
                    adiacentList.get(p1.getLocation()).add(p2.getLocation());
                }
            }
        }

        g2d.setColor(Color.BLACK);
        for (Point vertex : vertices) {
            g2d.fillOval(vertex.x - DOT_SIZE / 2, vertex.y - DOT_SIZE / 2, DOT_SIZE, DOT_SIZE);
        }

        Game startGame = new Game(vertices,adiacentList,frame);
        startGame.start();

    }

    public void clear() {
        this.setBackground(Color.WHITE);
        this.repaint();
    }
}
