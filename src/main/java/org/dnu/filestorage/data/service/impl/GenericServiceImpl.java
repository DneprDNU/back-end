package org.dnu.filestorage.data.service.impl;

import org.dnu.filestorage.data.dao.GenericDAO;
import org.dnu.filestorage.data.model.Identifiable;
import org.dnu.filestorage.data.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author demyura
 * @since 14.11.14
 */
@Transactional
public class GenericServiceImpl<D extends GenericDAO<T>, T extends Identifiable> implements GenericService<T> {
    @Autowired
    protected D dao;

    public GenericServiceImpl() {
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
        return dao.update(entity);
    }

    @Override
    public T create(T entity) {
        return dao.create(entity);
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
