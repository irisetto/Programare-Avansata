package org.example;
import jakarta.persistence.TypedQuery;
import org.example.entity.Artist;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ArtistRepository {
    private EntityManager entityManager;

    public ArtistRepository() {
        entityManager = PersistenceManager.getEntityManager();
    }

    public void create(Artist artist) {
        entityManager.getTransaction().begin();
        entityManager.persist(artist);
        entityManager.getTransaction().commit();
    }

    public Artist findById(int id) {
        return entityManager.find(Artist.class, id);
    }

    public List<Artist> findByName(String name) {
        TypedQuery<Artist> query = entityManager.createNamedQuery("Artist.findByName", Artist.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }
    public List<Artist> findAll() {
        TypedQuery<Artist> query = entityManager.createNamedQuery("Artist.findAll", Artist.class);

        return query.getResultList();
    }
    public void close() {
        entityManager.close();
    }
}
