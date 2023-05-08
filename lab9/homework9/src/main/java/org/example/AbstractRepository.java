package org.example;

import javax.persistence.EntityManager;

public abstract class AbstractRepository<T,ID> {
    private EntityManager entityManager;
    private Class<T> entityClass;

    public AbstractRepository(EntityManager entityManager, Class<T> entityClass) {
        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }
    public T save(T entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        return entity;
    }
    public T findById(ID id) {
        return entityManager.find(entityClass, id);
    }
    public void delete(T entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }
}
