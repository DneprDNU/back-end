package org.dnu.filestorage.data.service.impl;

import org.dnu.filestorage.data.dao.GenericDAO;
import org.dnu.filestorage.data.model.Identifiable;
import org.dnu.filestorage.data.service.GenericService;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author demyura
 * @since 14.11.14
 */
@Transactional
public class GenericServiceImpl<D extends GenericDAO<T>, T extends Identifiable> implements GenericService<T> {
    protected D dao;

    public GenericServiceImpl(D dao) {
        this.dao = dao;
    }

    @Override
    public List<T> list() {
        return dao.list();
    }

    @Override
    public T get(Long id) {
        return dao.get(id);
    }

    @Override
    public T update(T entity) {
        T currentEntity = dao.get(entity.getId());
        copyProperties(currentEntity, entity);
        return dao.update(currentEntity);
    }

    protected void copyProperties(T current, T newEntity) {
    }

    @Override
    public T create(T entity) {
        try {
            T currentEntity = dao.create(dao.getEntityClass().newInstance());
            copyProperties(currentEntity, entity);
            dao.update(currentEntity);
            return currentEntity;
        } catch (Exception e) {
            dao.create(entity);
        }
        return entity;
    }

    @Override
    public void remove(Long id) {
        dao.remove(get(id));
    }

    @Override
    public void remove(T entity) {
        dao.remove(entity);
    }
}
