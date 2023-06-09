package org.example;

import com.maxmind.geoip2.exception.GeoIp2Exception;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

public class MainFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel contentPane;
    public MainFrame() throws SQLException, IOException, GeoIp2Exception {
        // Setează proprietățile frame-ului principal
        setTitle("Aplicație Smart City");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        contentPane = new JPanel();
        // Creează un panel principal


        cardLayout = new CardLayout();
        contentPane.setLayout(cardLayout);

        JPanel mainPanel = new PrincipleMenu(this);
        contentPane.add(mainPanel,"main");
// Creează un panel pentru afișarea conținutului parcării
        JPanel parkingPanel1 = new ParkingPanel(this);
        contentPane.add(parkingPanel1, "parking1");

        JPanel parkingPanel2 = new ParkingPanel2(this);
        contentPane.add(parkingPanel2, "parking2");

        JPanel parkingPanel3 = new ParkingPanel3(this);
        contentPane.add(parkingPanel3, "parking3");

        JPanel parkingPanel4 = new ParkingPanel4(this);
        contentPane.add(parkingPanel4, "parking4");
    cardLayout.show(contentPane,"main");
        // Adaugă panelul principal în frame
        add(contentPane,BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }


    public JPanel getCardLayoutPanel() {
        return contentPane;
    }
}
