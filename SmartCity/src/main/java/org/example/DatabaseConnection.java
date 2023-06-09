package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import oracle.sql.STRUCT;
import java.awt.geom.Point2D;
import java.io.*;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Duration;
import java.util.function.Consumer;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.Location;

import java.io.File;
import java.net.InetAddress;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private  static Connection connection ;
    private DatabaseConnection() {

        try {
            connection = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "student");

            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    public static Connection getConnection() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return connection;
    }
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Database Connection Closing Failed : " + e.getMessage());
        }
    }
}