package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel dotsLabel, linesLabel;
    JSpinner dotsSpinner;
    JComboBox<Float> linesCombo;
    JButton createButton;
    int numDots;
    float edgeProb;

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        //create the label and the spinner
        // Create a decimal format with 1 decimal place
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        ArrayList<Float> values = new ArrayList<>();

// Add the float values to the list with a step of 0.1
        for (float i = 0; i <= 1.1; i += 0.1) {
            float value = Float.parseFloat(decimalFormat.format(i));
            values.add(value);
        }

        dotsLabel = new JLabel("Number of dots:");
        dotsSpinner = new JSpinner(new SpinnerNumberModel(6, 3, 100, 1));
        linesLabel = new JLabel("Line probability:");
        linesCombo = new JComboBox<>(values.toArray(new Float[0]));
        createButton = new JButton("Create");
        //create the rest of the components
        linesCombo.setSelectedItem(1.0f);
        createButton.setToolTipText("Create a new shape");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                edgeProb = (float) linesCombo.getSelectedItem();
                numDots = (int) dotsSpinner.getValue();
                DrawingPanel drawingPanel = frame.canvas;
                drawingPanel.setBackground(Color.white);
                drawingPanel.repaint();

            }
        });
        add(dotsLabel); //JPanel uses FlowLayout by default
        add(dotsSpinner);
        add(linesLabel);
        add(linesCombo);
        add(createButton);

    }

    public JSpinner getDotsSpinner() {
        return dotsSpinner;
    }

    public JComboBox getLinesCombo() {
        return linesCombo;
    }

    public JButton getCreateButton() {
        return createButton;
    }


}
