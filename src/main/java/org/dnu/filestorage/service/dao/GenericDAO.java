package org.dnu.filestorage.service.dao;

import org.dnu.filestorage.model.Identifiable;
import org.dnu.filestorage.model.NamedEntity;

import java.util.List;

/**
 * @author demyura
 * @since 15.10.14
 */
public interface GenericDAO<T extends Identifiable> {
    List<T> list();

    T get(Object id);

    T update(T entity);

    T create(T entity);

    void remove(Object id);

    default void remove(T entity) {
        remove(entity.getId());
    }
}
