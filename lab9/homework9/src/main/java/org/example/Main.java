package org.example;

import jakarta.persistence.*;
import org.example.entity.Album;
import org.example.entity.Artist;
import org.example.entity.Genre;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//
//        EntityManagerFactory emf =
//                Persistence.createEntityManagerFactory("ExamplePU");
//        EntityManager em = emf.createEntityManager();
//
//        em.getTransaction().begin();
//        //Artist artist = new Artist("Beatles");
//        //em.persist(artist);
//
//        Artist a = (Artist)em.createQuery(
//                        "select e from Artist e where e.name='Beatles'")
//                .getSingleResult();
//        //a.setName("The Beatles");
//
//        em.getTransaction().commit();
//        em.close();
//        emf.close();

        ArtistRepository artistRepository = new ArtistRepository();
        //Artist selena = new Artist("Selena Gomez");
        //artistRepository.create(selena);
        Artist artist = artistRepository.findById(1);
        System.out.println(artist.getName());
        List<Artist> artist2 = artistRepository.findByName("Zendaya");
        for (Artist artist3 : artist2) {
            System.out.println(artist3.getName());
        }
        List<Artist> artist4 = artistRepository.findAll();
        for (Artist artist5 : artist4) {
            System.out.println(artist5.getName()+" "+artist5.getId());
        }


        GenreRepository genreRepository = new GenreRepository();
        //Genre rock = new Genre("Rock");
        //genreRepository.create(rock);
        Genre genre = genreRepository.findById(1);
        System.out.println(genre.getName());
        List<Genre> genre2 = genreRepository.findByName("Pop");
        for (Genre genre3 : genre2) {
            System.out.println(genre3.getName());
        }
        List<Genre> genre4 = genreRepository.findAll();
        for (Genre genre5 : genre4) {
            System.out.println(genre5.getName()+" "+genre5.getId());
        }

        AlbumRepository albumRepository = new AlbumRepository();
        //Album ceva = new Album("ceva",2023,"ceva","ceva");
        //albumRepository.create(ceva);
        Album album = albumRepository.findById(1);
        System.out.println(album.getTitle());
        List<Album> album2 = albumRepository.findByTitle("Revolver");
        for (Album album3 : album2) {
            System.out.println(album3.getTitle());
        }
//        List<Album> album4 = albumRepository.findAll();
//        for (Album album5 : album4) {
//            System.out.println(album5.getTitle()+" "+album5.getId());
//        }
//
//        List<Artist> artists = new ArrayList<>();
//        for (int i = 1; i <= 1000; i++) {
//            Artist newArtist = new Artist();
//            newArtist.setName("Artist " + i);
//            artists.add(newArtist);
//        }
//        List<Album> albums = new ArrayList<>();
//        for (Artist artistI : artists) {
//
//            for (int i = 1; i <= 10; i++) {
//                Album newAlbum = new Album();
//                newAlbum.setTitle("Album " + i);
//                newAlbum.setRelease_year(2000 + i);
//                newAlbum.setArtist(artistI.getName());
//                albums.add(newAlbum);
//            }
//        }
//
//        for (Artist artistI : artists) {
//            artistRepository.create(artistI);
//        }
//        for (Album albumI:albums) {
//            albumRepository.create(albumI);
//        }
        // start timing
        long startTime = System.nanoTime();
        List<Artist> findArtist = artistRepository.findAll();
// end timing
        long endTime = System.nanoTime();

        long duration = endTime - startTime;

        System.out.println("Execution time: " + duration + " nanoseconds");

        PersistenceManager.getEntityManager().close();
    }
}
