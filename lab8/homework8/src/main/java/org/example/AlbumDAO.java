package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.sun.jdi.Value;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
    public void load(String file) throws IOException, CsvException, SQLException {
        CSVReader reader = new CSVReader(new FileReader(file));
        String[] data = reader.readNext();
        String sql = "INSERT INTO albums (id,release_year,title,artist,genre) values (?,?,?,?,?) ";
        PreparedStatement pstmt = con.prepareStatement(sql);

        String[] values;
        while((values = reader.readNext()) != null ) {
            pstmt.setInt(1,Integer.parseInt(values[0]));
            pstmt.setInt(2,Integer.parseInt(values[1]));
            pstmt.setString(3,values[2]);
            pstmt.setString(4,values[3]);
            pstmt.setString(5,values[4]);
            pstmt.executeUpdate();

        }
    }
    public void delete(int id){
        try(PreparedStatement pstmt = con.prepareStatement(
                "delete from albums where id = ?"
        )){
            pstmt.setInt(1,id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
