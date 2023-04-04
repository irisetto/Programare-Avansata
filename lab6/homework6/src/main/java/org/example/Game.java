package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.List;

public class Game extends JPanel implements Serializable {
    List<Point> vertices;
    Map<Point, List<Point>> adiacentList;
    int currentPlayer;
    MainFrame frame;
    Map<Point, List<Point>> player1Edges = new HashMap<>();
    Map<Point, List<Point>> player2Edges = new HashMap<>();
    Set<Pair<Point, Point>> coloredEdges = new HashSet<>();
    DrawingPanel drawingPanel;

    public Game(List<Point> vertices, Map<Point, List<Point>> adiacentList, MainFrame frame) {
        this.vertices = vertices;
        this.adiacentList = adiacentList;
        this.currentPlayer = 1;
        this.frame = frame;
        drawingPanel = frame.canvas;
    }

    void start() {

        drawingPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                Point click = e.getPoint();
                for (Map.Entry<Point, List<Point>> entry : adiacentList.entrySet()) {
                    Point startPoint = entry.getKey();
                    for (Point endPoint : entry.getValue()) {
                        if (isOnEdge(click, startPoint, endPoint))
                            if (!edgeIsColored(startPoint, endPoint)) {
                                Graphics2D g2d = (Graphics2D) drawingPanel.getGraphics();
                                Graphics2D g = (Graphics2D) drawingPanel.image.getGraphics();
                                g.setColor(currentPlayer == 1 ? Color.red : Color.blue);
                                g.setStroke(new BasicStroke(4));

                                g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
                                g.dispose();
                                g2d.setColor(currentPlayer == 1 ? Color.red : Color.blue);
                                g2d.setStroke(new BasicStroke(4));
                                g2d.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
                                g2d.dispose();
                                coloredEdges.add(new Pair<>(startPoint, endPoint));

                                if (currentPlayer == 1) {
                                    player1Edges.putIfAbsent(startPoint, new ArrayList<>());
                                    player1Edges.putIfAbsent(endPoint, new ArrayList<>());
                                    player1Edges.get(startPoint).add(endPoint);
                                    player1Edges.get(endPoint).add(startPoint);
                                    if (checkTriangle(player1Edges)) {
                                        JOptionPane.showMessageDialog(frame, "Player " + currentPlayer + " wins!");
                                        JOptionPane.getRootFrame().setLocationRelativeTo(frame);
                                        reset();
                                    }
                                } else {
                                    player2Edges.putIfAbsent(startPoint, new ArrayList<>());
                                    player2Edges.putIfAbsent(endPoint, new ArrayList<>());
                                    player2Edges.get(startPoint).add(endPoint);
                                    player2Edges.get(endPoint).add(startPoint);
                                    if (checkTriangle(player2Edges)) {
                                        JOptionPane.showMessageDialog(frame, "Player " + currentPlayer + " wins!");
                                        JOptionPane.getRootFrame().setLocationRelativeTo(frame);
                                        reset();
                                    }
                                }
                                currentPlayer = currentPlayer == 1 ? 2 : 1;
                                repaint();
                                g.drawImage(drawingPanel.image, 0, 0, drawingPanel);
                            }

                    }
                }
            }
        });

    }

    private void reset() {
        currentPlayer = 1;
        player1Edges.clear();
        player2Edges.clear();

        for (Pair p : coloredEdges) {
            p.remove();
        }
        coloredEdges.clear();
        player1Edges = new HashMap<>();
        player2Edges = new HashMap<>();
        coloredEdges = new HashSet<>();
        vertices = new LinkedList<>();
        adiacentList = new HashMap<>();
        frame = new MainFrame();
        drawingPanel.clear();
        drawingPanel = frame.canvas;
        this.setBackground(Color.WHITE);
        repaint();
    }

    private boolean checkTriangle(Map<Point, List<Point>> playerEdges) {
        for (Map.Entry<Point, List<Point>> entry : playerEdges.entrySet()) {
            for (Point endPoint : entry.getValue()) {
                for (Point k : playerEdges.get(endPoint)) {
                    if (entry.getValue().contains(k))
                        return true;
                }
            }

        }
        return false;
    }

    private boolean edgeIsColored(Point startPoint, Point endPoint) {
        Pair<Point, Point> pair = new Pair<>(startPoint, endPoint);
        return coloredEdges.contains(pair);
    }

    private boolean isOnEdge(Point p, Point start, Point end) {
        double distance = start.distance(end);
        double d1 = p.distance(start);
        double d2 = p.distance(end);
        return Math.abs(distance - (d1 + d2)) < 0.2;
    }
}
