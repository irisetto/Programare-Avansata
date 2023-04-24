package org.example;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        try {
            var artists = new ArtistDAO();
//            artists.create(1,"Labrinth");
//            artists.create(2,"Zendaya");
            System.out.println(artists.findByName("Zendaya"));
            System.out.println(artists.findById(2));
            System.out.println(artists.getAll());
            var albums = new AlbumDAO();
          //  albums.create(1,2019,"Euphoria","Labrinth","Gospel");
           // albums.create(2,2022,"Motomami","Rosalia","Reggaeton");
            System.out.println(albums.findById(1));
            System.out.println(albums.getAll());
            Database.getConnection().commit();
            Database.getConnection().close();
        } catch (SQLException e) {
            System.err.println(e);
            Database.getConnection().rollback();
        }

    }
}