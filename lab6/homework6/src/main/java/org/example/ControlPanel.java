package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton exitBtn = new JButton("Exit");
    //create all buttons (Load, Exit, etc.)
    JButton loadBtn = new JButton("Load");
    JButton saveBtn = new JButton("Save");
    JButton exportBtn = new JButton("Export");

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        //change the default layout manager (just for fun)
        setLayout(new GridLayout(1, 4));

        add(exitBtn);
        add(loadBtn);
        add(saveBtn);
        add(exportBtn);
        //configure listeners for all buttons
        exitBtn.addActionListener(this::exitGame);
        exportBtn.addActionListener(this::exportPng);

    }

    private void exportPng(ActionEvent actionEvent) {
        System.out.println("se incearca export");

        try {
            ImageIO.write(frame.canvas.image, "png", new File("game_board.png"));
            JOptionPane.showMessageDialog(frame, "Image saved to game_board.png");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error saving image: " + e.getMessage());
        }
    }

    private void exitGame(ActionEvent e) {
        frame.dispose();
    }
    //...TODO
}