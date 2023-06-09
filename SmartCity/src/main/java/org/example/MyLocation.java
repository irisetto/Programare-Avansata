package org.example;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.Location;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;

public class MyLocation {

    private static double longitude;
    private static double latitude;

    public static void calculateCoords() throws IOException, GeoIp2Exception {
        File database = new File("C:\\Users\\tcohm\\Downloads\\GeoLite2-City_20230606\\GeoLite2-City_20230606\\GeoLite2-City.mmdb");
        DatabaseReader reader = new DatabaseReader.Builder(database).build();
       //String ip = "79.112.117.129";
        URL url = new URL("https://api.ipify.org"); // Service that returns your public IP address
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader2 = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String ip = reader2.readLine();
        reader2.close();

        System.out.println("Public IPv4 Address: " + ip);
        // Obțineți adresa IP a utilizatorului curent
        InetAddress ipAddress = InetAddress.getByName(ip);

        // Obțineți informațiile despre oraș și locație
        CityResponse cityResponse = reader.city(ipAddress);
        Location location = cityResponse.getLocation();

        // Afișați coordonatele
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        System.out.println("Coordonate: " + latitude + ", " + longitude);

    }

    public static double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public static double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
