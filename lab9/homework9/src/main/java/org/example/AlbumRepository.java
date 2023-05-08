package org.example;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.EntityManager;
import org.example.entity.Album;

import java.util.List;

public class AlbumRepository {
    private EntityManager entityManager;

    public AlbumRepository() {
        entityManager = PersistenceManager.getEntityManager();
    }

    public void create(Album album) {
        entityManager.getTransaction().begin();
        entityManager.persist(album);
        entityManager.getTransaction().commit();
    }

    public Album findById(int id) {
        return entityManager.find(Album.class, id);
    }

    public List<Album> findByTitle(String name) {
        TypedQuery<Album> query = entityManager.createNamedQuery("Album.findByTitle", Album.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }
    public List<Album> findAll() {
        TypedQuery<Album> query = entityManager.createNamedQuery("Album.findAll", Album.class);

        return query.getResultList();
    }
    public void close() {
        entityManager.close();
    }
}
