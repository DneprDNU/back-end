package org.dnu.filestorage.service.dao.impl;

import org.dnu.filestorage.model.LinkingEntity;
import org.dnu.filestorage.service.dao.LinkingEntityDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author demyura
 * @since 14.11.14
 */
@Repository
@Transactional
public class LinkingEntityDAOImpl implements LinkingEntityDAO {

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public List<LinkingEntity> list() {
        return entityManager.createQuery("select a from LinkingEntity a", LinkingEntity.class).getResultList();
    }

    @Override
    public LinkingEntity get(Long id) {
        return entityManager.find(LinkingEntity.class, id);
    }

    @Override
    public LinkingEntity update(LinkingEntity entity) {
        return entityManager.merge(entity);
    }

    @Override
    public LinkingEntity create(LinkingEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public void remove(LinkingEntity entity) {
        entityManager.remove(entity);
    }
}
