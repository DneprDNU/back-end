package org.dnu.filestorage.service.dao;

import org.dnu.filestorage.model.NamedEntity;

import java.util.List;

/**
 * @author demyura
 * @since 15.10.14
 */
public interface GenericDAO<T extends NamedEntity> {
    List<T> list();

    T get(Long id);

    T update(T entity);

    T create(T entity);

    void remove(Long id);

    default void remove(T entity) {
        remove(entity.getId());
    }
}
