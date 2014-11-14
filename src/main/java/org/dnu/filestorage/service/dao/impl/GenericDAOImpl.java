package org.dnu.filestorage.service.dao.impl;

import org.dnu.filestorage.model.NamedEntity;
import org.dnu.filestorage.service.dao.GenericDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author demyura
 * @since 14.11.14
 */
@Transactional
public class GenericDAOImpl<T extends NamedEntity> implements GenericDAO<T> {
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
        return entityManager.createQuery("select a from " + entityClass.getSimpleName() + " a", entityClass).getResultList();
    }

    @Override
    public T get(Long id) {
        return entityManager.find(entityClass, id);
    }

    @Override
    public T saveOfUpdate(T entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void remove(Long id) {
        entityManager.remove(get(id));
    }

    @Override
    public void remove(T entity) {
        entityManager.remove(entity);
    }
}
