package org.dnu.filestorage.data.dao.impl;

import org.dnu.filestorage.controller.generic.GenericController;
import org.dnu.filestorage.data.dao.GenericDAO;
import org.dnu.filestorage.data.model.Identifiable;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author demyura
 * @since 14.11.14
 */
@Transactional
public class GenericDAOImpl<T extends Identifiable> implements GenericDAO<T> {
    @PersistenceContext
    protected EntityManager entityManager;

    protected Class<T> entityClass;

    public GenericDAOImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
                .getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass
                .getActualTypeArguments()[0];
    }

    @Override
    public List<T> list() {
        return entityManager.createQuery("select a from " + entityClass.getSimpleName() + " a order by a.name asc"
                , entityClass).getResultList();
    }


    @Override
    public T get(Object id) {
        try {
            return entityManager.find(entityClass, id);
        } catch (NoResultException | NullPointerException e) {
            throw new GenericController.NotFoundException();
        }
    }

    @Override
    public T update(T entity) {
        return entityManager.merge(entity);
    }

    @Override
    public T create(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public void remove(Object id) {
        entityManager.remove(get(id));
    }

    @Override
    public void remove(T entity) {
        entityManager.remove(entity);
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }

    @Override
    public long getCount() {
        return (long) entityManager.createQuery("select count(a) from " + entityClass.getSimpleName() + " a")
                .getSingleResult();
    }

    @Override
    public List<T> list(int from, int to) {
        TypedQuery<T> query = entityManager.createQuery("select a from " + entityClass.getSimpleName() + " a " +
                        "order by a.name asc",
                entityClass);
        query.setFirstResult(from);
        query.setMaxResults(to - from);
        return query.getResultList();
    }
}
