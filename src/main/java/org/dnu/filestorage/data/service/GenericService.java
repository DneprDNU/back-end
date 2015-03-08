package org.dnu.filestorage.data.service;

import org.dnu.filestorage.data.model.Identifiable;

import java.util.List;

/**
 * @author demyura
 * @since 15.10.14
 */
public interface GenericService<T extends Identifiable> {
    List<T> list();

    T get(Long id);

    T update(T entity);

    T create(T entity);

    void remove(Long id);

    long getCount();

    List<T> list(int from, int to);

    default void remove(T entity) {
        remove(entity.getId());
    }
}
