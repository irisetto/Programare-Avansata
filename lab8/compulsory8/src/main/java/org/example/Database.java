package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Database instance;
    private  static Connection connection ;
    private  static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private  static final String username = "postgres";
    private  static final String password = "student";
    private Database()  {
        try {
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    public static Connection getConnection() {
        if (instance == null) {
            instance = new Database();
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
