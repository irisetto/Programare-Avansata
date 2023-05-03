package org.example;

import com.sun.jdi.Value;

import java.security.Key;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenreDAO {
    private Connection con;


    public GenreDAO() throws SQLException {
        this.con = Database.getConnection();
    }

    public void create(int id,String name) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into genres (id,name) values (?,?)")) {
            pstmt.setInt(1,id);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
        }
    }
    public Integer findByName(String name) throws SQLException {

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from genres where name='" + name + "'")) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }
    public String findById(int id) throws SQLException {
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select name from genres where id=" + id)) {
            return rs.next() ? rs.getString(1) : null;
        }
    }
    public Map<Integer, String> getAll() throws SQLException {
        Map<Integer,String> genres = new HashMap();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select * from genres")) {

            while (rs.next()) {

                genres.put(rs.getInt("id"),rs.getString("name"));
            }

            return genres;
        }
    }

}
