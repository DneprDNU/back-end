package org.dnu.filestorage.data.service.impl;

import org.dnu.filestorage.data.dao.ResourceDAO;
import org.dnu.filestorage.data.model.Resource;
import org.dnu.filestorage.data.service.ResourceService;
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
public class ResourceServiceImpl extends GenericServiceImpl<ResourceDAO, Resource> implements ResourceService {

    @Autowired
    public ResourceServiceImpl(ResourceDAO dao) {
        super(dao);
    }

    @Override
    public List<Resource> listByCategoryId(Long categoryId) {
        return dao.listByCategoryId(categoryId);
    }

    @Override
    public List<Resource> listResourcesByTeacherIdByLinks(Long teacherId) {
        return listResourcesByTeacherIdByLinks(teacherId);
    }

    @Override
    public Resource get(Long id) {
        return dao.get(id);
    }
}
