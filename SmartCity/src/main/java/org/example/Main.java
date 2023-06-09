package org.example;

import com.maxmind.geoip2.exception.GeoIp2Exception;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IOException, GeoIp2Exception {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);

    }
}