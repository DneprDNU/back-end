package org.dnu.filestorage.data.service;

import org.dnu.filestorage.data.model.LinkingEntity;

import java.util.List;

/**
 * @author demyura
 * @since 15.10.14
 */
public interface LinkingEntityService {
    List<LinkingEntity> list();

    LinkingEntity get(Long id);

    LinkingEntity update(LinkingEntity entity);

    LinkingEntity create(LinkingEntity entity);

    void remove(LinkingEntity entity);
}
