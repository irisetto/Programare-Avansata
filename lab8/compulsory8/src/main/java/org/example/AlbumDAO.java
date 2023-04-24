package org.example;

import com.sun.jdi.Value;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlbumDAO {
    private Connection con;

    public AlbumDAO() throws SQLException {
        this.con = Database.getConnection();
    }

    public void create(int id, int releaseYear, String title, String artist, String genre) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into albums values (?,?,?,?,?)")) {
            pstmt.setInt(1,id);
            pstmt.setInt(2,releaseYear);
            pstmt.setString(3, title);
            pstmt.setString(4, artist);
            pstmt.setString(5, genre);
            pstmt.executeUpdate();
        }
    }
    public Integer findByName(String name) throws SQLException {

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from artists where name='" + name + "'")) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }
    public String findById(int id) throws SQLException {
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select title from albums where id=" + id)) {
            return rs.next() ? rs.getString(1) : null;
        }
    }
    public List<String> getAll() throws SQLException {
        List<String> albums = new ArrayList<>();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select * from albums")) {

            while (rs.next()) {
                albums.add(String.valueOf(rs.getInt("id")) + " | " + String.valueOf(rs.getInt("release_year")) + " | " + rs.getString("title") + " | " + rs.getString("artist") + " | " + rs.getString("genre") + "\n");
            }

            return albums;
        }
    }

}
