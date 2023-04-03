package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton exitBtn = new JButton("Exit");
    //create all buttons (Load, Exit, etc.)
    JButton loadBtn = new JButton("Load");
    JButton saveBtn = new JButton("Save");
    public ControlPanel(MainFrame frame) {
        this.frame = frame; init();
    }
    private void init() {
        //change the default layout manager (just for fun)
        setLayout(new GridLayout(1, 4));
        //add all buttons ...TODO
        add(exitBtn);
        add(loadBtn);
        add(saveBtn);
        //configure listeners for all buttons
        exitBtn.addActionListener(this::exitGame);
 //...TODO
    }
    private void exitGame(ActionEvent e) {
        frame.dispose();
    }
 //...TODO
}