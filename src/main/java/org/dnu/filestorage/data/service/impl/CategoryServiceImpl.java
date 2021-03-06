package org.dnu.filestorage.data.service.impl;

import org.dnu.filestorage.data.dao.CategoryDAO;
import org.dnu.filestorage.data.model.Category;
import org.dnu.filestorage.data.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author demyura
 * @since 14.11.14
 */
@Service
@Transactional
public class CategoryServiceImpl extends GenericServiceImpl<CategoryDAO, Category> implements CategoryService {

    @Autowired
    public CategoryServiceImpl(CategoryDAO dao) {
        super(dao);
    }

    @Override
    protected void copyProperties(Category current, Category newEntity) {
        current.setName(newEntity.getName());
        current.setImage(newEntity.getImage());
    }
}
