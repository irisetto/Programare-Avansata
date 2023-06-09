package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class ParkingPanel2 extends JPanel {
    private JPanel parkingPanel;
    private Connection con;
    private String name;
    private static int capacity;
    private static int occupied;
    private static double locationLat;
    private static double locationLong;
    private double pricePerHour;

    public ParkingPanel2(MainFrame mainFrame) throws SQLException {
        this.con = DatabaseConnection.getConnection();
        String selectQuery = "SELECT * FROM parking where id=2";
        PreparedStatement statement = con.prepareStatement(selectQuery);
        ResultSet resultSet = statement.executeQuery();

        if (!resultSet.isBeforeFirst()) {
            System.out.println("No data found.");
        } else {
            while (resultSet.next()) {
                // Retrieve and print the data
                int id = resultSet.getInt("id");
                name = resultSet.getString("name");
                capacity = resultSet.getInt("capacity");
                locationLat =  resultSet.getDouble("location_lat");
                locationLong =  resultSet.getDouble("location_long");
                pricePerHour = resultSet.getDouble("price_per_hour");

                System.out.println("ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Capacity: " + capacity);
                System.out.println("Location Latitude: " + locationLat);
                System.out.println("Location Longitude: " + locationLong);
                System.out.println("Price Per Hour: " + pricePerHour);
                // Print other columns as needed
            }
        }

        resultSet.close();
        statement.close();

        parkingPanel = new JPanel(new BorderLayout());

        JPanel backBut= new JPanel();
        JButton back = new JButton("Inapoi");
        backBut.add(back);
        parkingPanel.add(backBut, BorderLayout.NORTH);
        JPanel info = new JPanel(new FlowLayout());

        JLabel nume = new JLabel("Nume: "+name);
        JLabel capacitate = new JLabel("Capacitate: "+String.valueOf(capacity));
        JLabel pret = new JLabel("Pret/ora: "+String.valueOf(pricePerHour));
        nume.setPreferredSize(new Dimension(220, 30));
        capacitate.setPreferredSize(new Dimension(200, 30));
        pret.setPreferredSize(new Dimension(120, 30));
        Font font = nume.getFont();
        Font fontMare = font.deriveFont(font.getSize() + 5f); // Mărimea fontului mărită cu 5
        nume.setFont(fontMare);
        capacitate.setFont(fontMare);
        pret.setFont(fontMare);
        info.add(nume);
        info.add(capacitate);
        info.add(pret);
        parkingPanel.add(info,BorderLayout.CENTER);
        //locuri de parcare
        occupied = generateRandomOccupied(capacity);
        DrawParking drawPanel = new DrawParking(capacity,occupied);
        parkingPanel.add(drawPanel,BorderLayout.SOUTH);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel = mainFrame.getCardLayoutPanel();
                CardLayout layout = (CardLayout) panel.getLayout();
                layout.show(panel,"main");
            }
        });
        add(parkingPanel);
    }
    private int generateRandomOccupied(int capacity) {
        Random random = new Random();
        return random.nextInt(capacity + 1);
    }
    public JPanel getParkingPanel() {
        return parkingPanel;
    }

    public void setParkingPanel(JPanel parkingPanel) {
        this.parkingPanel = parkingPanel;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public static int getOccupied() {
        return occupied;
    }

    public static int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public static double getLocationLat() {
        return locationLat;
    }

    public void setLocationLat(double locationLat) {
        this.locationLat = locationLat;
    }

    public static double getLocationLong() {
        return locationLong;
    }

    public void setLocationLong(double locationLong) {
        this.locationLong = locationLong;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }
}
