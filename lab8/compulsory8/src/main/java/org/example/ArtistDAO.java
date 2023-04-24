package org.example;

import com.sun.jdi.Value;

import java.security.Key;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArtistDAO {
    private Connection con;
    int cod;
    String nume;

    public ArtistDAO() throws SQLException {
        this.con = Database.getConnection();
    }

    public void create(int id,String name) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into artists (id,name) values (?,?)")) {
            pstmt.setInt(1,id);
            pstmt.setString(2, name);
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
                     "select name from artists where id=" + id)) {
            return rs.next() ? rs.getString(1) : null;
        }
    }
    public Map<Integer, String> getAll() throws SQLException {
        Map<Integer,String> artists = new HashMap();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select * from artists")) {

            while (rs.next()) {

                artists.put(rs.getInt("id"),rs.getString("name"));
            }

            return artists;
        }
    }

}
