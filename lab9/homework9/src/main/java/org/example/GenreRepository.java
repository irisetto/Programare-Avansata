package org.example;
import jakarta.persistence.TypedQuery;
import org.example.entity.Artist;
import jakarta.persistence.EntityManager;
import org.example.entity.Genre;

import java.util.List;

public class GenreRepository {
    private EntityManager entityManager;

    public GenreRepository() {
        entityManager = PersistenceManager.getEntityManager();
    }

    public void create(Genre genre) {
        entityManager.getTransaction().begin();
        entityManager.persist(genre);
        entityManager.getTransaction().commit();
    }

    public Genre findById(int id) {
        return entityManager.find(Genre.class, id);
    }

    public List<Genre> findByName(String name) {
        TypedQuery<Genre> query = entityManager.createNamedQuery("Genre.findByName", Genre.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }
    public List<Genre> findAll() {
        TypedQuery<Genre> query = entityManager.createNamedQuery("Genre.findAll", Genre.class);

        return query.getResultList();
    }
    public void close() {
        entityManager.close();
    }
}
