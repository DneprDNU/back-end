package org.dnu.filestorage.service.dao.impl;

import org.dnu.filestorage.model.Resource;
import org.dnu.filestorage.service.dao.ResourceDAO;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author demyura
 * @since 14.11.14
 */
@Repository
@Transactional
public class ResourceDAOImpl extends GenericDAOImpl<Resource> implements ResourceDAO {

    @Override
    public List<Resource> listByCategoryId(Long categoryId) {
        return entityManager.createNamedQuery("getResourcesByCategoryId", Resource.class)
                .setParameter("categoryId", categoryId).getResultList();
    }

    @Override
    public Resource get(Object id) {
        Resource result = super.get(id);
        result.getSubjects().size();  //initialization
        result.getCategories().size();
        return result;
    }
}
