package org.example.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "albums")
@NamedQueries({
        @NamedQuery(name = "Album.findAll",
                query = "select e from Album e order by e.id"),
        @NamedQuery(name = "Album.findByTitle",
                query = "select e from Album e where e.title like :name  "),
})

public class Album implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;
    @Column(name = "release_year")
    private Integer release_year;
    @Column(name = "artist")
    private String artistName;
    @Column(name = "genre")
    private String genre;
    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;
    @ManyToMany
    @JoinTable(name = "album_genres",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres = new ArrayList<>();

    public Album() {

    }

    public Album(String title, Integer release_year, String artist1, String genre) {
        this.title = title;
        this.release_year = release_year;
        this.artistName = artist1;
        this.genre = genre;
    }



    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Integer getRelease_year() {
        return release_year;
    }

    public void setRelease_year(Integer release_year) {
        this.release_year = release_year;
    }

    public String getArtist() {
        return artistName;
    }

    public void setArtist(String artist) {
        this.artistName = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
