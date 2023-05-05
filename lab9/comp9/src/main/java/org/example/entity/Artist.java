package org.example.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "artists")
@NamedQueries({
        @NamedQuery(name = "Artist.findAll",
                query = "select e from Artist e order by e.id"),
        @NamedQuery(name = "Artist.findByName",
                query = "select e from Artist e where e.name like :name  "),
})

public class Artist implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")

    private String name;


    public Artist() {

    }

    public Artist(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
