package org.example;

import com.maxmind.geoip2.exception.GeoIp2Exception;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PrincipleMenu extends JPanel {
    private double myLat;
    private double myLong;

    public PrincipleMenu(MainFrame mainFrame) throws IOException, GeoIp2Exception {
        JPanel mainPanel = new JPanel(new BorderLayout());
        Font buttonFont = new Font("Verdana", Font.PLAIN, 18);
        Font buttonFontUp = new Font("Verdana", Font.PLAIN, 13);
        // Creează butoanele de sus
        JButton findNearestButton = new JButton("Găsește parcarea cea mai apropiată");
        findNearestButton.setPreferredSize(new Dimension(300,50));
        findNearestButton.setFont(buttonFontUp);
        JButton findAvailableButton = new JButton("Găsește parcarea cea mai liberă");
        findAvailableButton.setPreferredSize(new Dimension(300,50));
        findAvailableButton.setFont(buttonFontUp);
        JPanel topPanel = new JPanel(new FlowLayout());
        // Adaugă butoanele de sus în panelul principal
        topPanel.add(findNearestButton);
        topPanel.add(findAvailableButton);
        // Creează butoanele de jos
        JButton parking1Button = new JButton("Parcare 1");
        JButton parking2Button = new JButton("Parcare 2");
        JButton parking3Button = new JButton("Parcare 3");
        JButton parking4Button = new JButton("Parcare 4");
        //font butoanele de jos

        parking1Button.setFont(buttonFont);
        parking2Button.setFont(buttonFont);
        parking3Button.setFont(buttonFont);
        parking4Button.setFont(buttonFont);
        // marime butoane de jos
        Dimension buttonSize = new Dimension(200, 100);
        parking1Button.setPreferredSize(buttonSize);
        parking2Button.setPreferredSize(buttonSize);
        parking3Button.setPreferredSize(buttonSize);
        parking4Button.setPreferredSize(buttonSize);
        // Adaugă butoanele de jos în panelul principal
        JPanel bottomPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(30, 30, 30, 30); // Adaugă spațiu între butoane
        gbc.gridx = 0;
        gbc.gridy = 0;
        bottomPanel.add(parking1Button, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        bottomPanel.add(parking2Button, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        bottomPanel.add(parking3Button, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        bottomPanel.add(parking4Button, gbc);

        JPanel verticalSpacePanel = new JPanel();
        verticalSpacePanel.setPreferredSize(new Dimension(0, 30));

        // Adaugă toate panourile în panoul principal
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(verticalSpacePanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        MyLocation.calculateCoords();
        myLat = MyLocation.getLatitude();
        myLong = MyLocation.getLongitude();

        System.out.println(myLat+" "+myLong);
        //button listener
        findAvailableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int available1 = ParkingPanel.getCapacity()-ParkingPanel.getOccupied() ;
                int available2 = ParkingPanel2.getCapacity()-ParkingPanel2.getOccupied();
                int available3 = ParkingPanel3.getCapacity()-ParkingPanel3.getOccupied();
                int available4 = ParkingPanel4.getCapacity()-ParkingPanel4.getOccupied();

                int mostAvailable = Math.max(available1,Math.max(available2,Math.max(available3,available4)));
                String numeParcareCeaMaiLibera = "";
                if (mostAvailable == available1) {
                    numeParcareCeaMaiLibera = "parking1";
                } else if (mostAvailable == available2) {
                    numeParcareCeaMaiLibera = "parking2";
                } else if(mostAvailable == available3){
                    numeParcareCeaMaiLibera = "parking3";
                }
                else {
                    numeParcareCeaMaiLibera = "parking4";
                }
                JPanel panel = mainFrame.getCardLayoutPanel();
                CardLayout layout = (CardLayout) panel.getLayout();
                layout.show(panel,numeParcareCeaMaiLibera);

            }
        });
        findNearestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double distantaParcare1 = calculDistanta(myLat, myLong, ParkingPanel.getLocationLat(), ParkingPanel.getLocationLong());
                double distantaParcare2 = calculDistanta(myLat, myLong, ParkingPanel2.getLocationLat(), ParkingPanel2.getLocationLong());
                double distantaParcare3 = calculDistanta(myLat, myLong, ParkingPanel3.getLocationLat(), ParkingPanel3.getLocationLong());
                double distantaParcare4 = calculDistanta(myLat, myLong, ParkingPanel4.getLocationLat(), ParkingPanel4.getLocationLong());

                double distantaMinima = Math.min(distantaParcare1, Math.min(distantaParcare2,Math.min(distantaParcare3, distantaParcare4)));
                String numeParcareCeaMaiApropiata = "";

                if (distantaMinima == distantaParcare1) {
                    numeParcareCeaMaiApropiata = "parking1";
                } else if (distantaMinima == distantaParcare2) {
                    numeParcareCeaMaiApropiata = "parking2";
                } else if(distantaMinima == distantaParcare3){
                    numeParcareCeaMaiApropiata = "parking3";
                }
                else {
                    numeParcareCeaMaiApropiata = "parking4";
                }
                JPanel panel = mainFrame.getCardLayoutPanel();
                CardLayout layout = (CardLayout) panel.getLayout();
                layout.show(panel,numeParcareCeaMaiApropiata);

            }
        });
        parking1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel = mainFrame.getCardLayoutPanel();
                CardLayout layout = (CardLayout) panel.getLayout();
                layout.show(panel,"parking1");
            }
        });
        parking2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel = mainFrame.getCardLayoutPanel();
                CardLayout layout = (CardLayout) panel.getLayout();
                layout.show(panel,"parking2");
            }
        });
        parking3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel = mainFrame.getCardLayoutPanel();
                CardLayout layout = (CardLayout) panel.getLayout();
                layout.show(panel,"parking3");
            }
        });
        parking4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel = mainFrame.getCardLayoutPanel();
                CardLayout layout = (CardLayout) panel.getLayout();
                layout.show(panel,"parking4");
            }
        });

        add(mainPanel);

    }
    // Metodă pentru calcularea distanței între două puncte pe sfera Pământului utilizând formula Haversine
    private static double calculDistanta(double latitudine1, double longitudine1, double latitudine2, double longitudine2) {
        double razaPamantuluiKm = 6371.0; // Raza Pământului în kilometri

        // Convertirea coordonatelor în radiani
        double latitudineRad1 = Math.toRadians(latitudine1);
        double longitudineRad1 = Math.toRadians(longitudine1);
        double latitudineRad2 = Math.toRadians(latitudine2);
        double longitudineRad2 = Math.toRadians(longitudine2);

        // Diferența dintre latitudine și longitudine
        double diferentaLatitudine = latitudineRad2 - latitudineRad1;
        double diferentaLongitudine = longitudineRad2 - longitudineRad1;

        // Calculul distanței utilizând formula Haversine
        double a = Math.sin(diferentaLatitudine / 2) * Math.sin(diferentaLatitudine / 2) +
                Math.cos(latitudineRad1) * Math.cos(latitudineRad2) *
                        Math.sin(diferentaLongitudine / 2) * Math.sin(diferentaLongitudine / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distanta = razaPamantuluiKm * c;

        return distanta;
    }


}
