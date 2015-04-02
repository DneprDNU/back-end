package org.dnu.filestorage.data.dao.impl;

import org.dnu.filestorage.data.dao.LinkingEntityDAO;
import org.dnu.filestorage.data.model.LinkingEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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

    @Override
    public long getCount() {
        return (long) entityManager.createQuery("select count(a) from LinkingEntity a")
                .getSingleResult();
    }

    @Override
    public List<LinkingEntity> list(int from, int to) {
        TypedQuery<LinkingEntity> query = entityManager.createQuery("select a from LinkingEntity a",
                LinkingEntity.class);
        query.setFirstResult(from);
        query.setMaxResults(to - from);
        return query.getResultList();
    }
}
