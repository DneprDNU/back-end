package org.dnu.filestorage.data.service.impl;

import org.dnu.filestorage.data.dao.LinkingEntityDAO;
import org.dnu.filestorage.data.model.LinkingEntity;
import org.dnu.filestorage.data.service.LinkingEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author demyura
 * @since 14.11.14
 */
@Service
@Transactional
public class LinkingEntityServiceImpl implements LinkingEntityService {

    @Autowired
    private LinkingEntityDAO dao;

    @Override
    public List<LinkingEntity> list() {
        return dao.list();
    }

    @Override
    public LinkingEntity get(Long id) {
        return dao.get(id);
    }

    @Override
    public LinkingEntity update(LinkingEntity entity) {
        return dao.update(entity);
    }

    @Override
    public LinkingEntity create(LinkingEntity entity) {
        return dao.create(entity);
    }

    @Override
    public void remove(LinkingEntity entity) {
        dao.remove(entity);
    }
}
