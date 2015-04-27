package org.dnu.filestorage.data.service.impl;

import org.dnu.filestorage.data.dao.FreeResourceCategoryDAO;
import org.dnu.filestorage.data.model.FreeResourceCategory;
import org.dnu.filestorage.data.service.FreeResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author demyura
 * @since 14.11.14
 */
@Service
@Transactional
public class FreeResourceCategoryServiceImpl extends GenericServiceImpl<FreeResourceCategoryDAO, FreeResourceCategory>
        implements FreeResourceCategoryService {

    @Autowired
    public FreeResourceCategoryServiceImpl(FreeResourceCategoryDAO dao) {
        super(dao);
    }

    @Override
    protected void copyProperties(FreeResourceCategory current, FreeResourceCategory newEntity) {
        current.setName(newEntity.getName());
        current.setImage(newEntity.getImage());
    }
}
