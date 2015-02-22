package org.dnu.filestorage.data.service.impl;

import org.dnu.filestorage.data.dao.CategoryDAO;
import org.dnu.filestorage.data.dao.FreeResourceDAO;
import org.dnu.filestorage.data.model.Category;
import org.dnu.filestorage.data.model.FreeResource;
import org.dnu.filestorage.data.service.FreeResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author demyura
 * @since 14.11.14
 */
@Service
@Transactional
public class FreeResourceServiceImpl extends GenericServiceImpl<FreeResourceDAO, FreeResource>
        implements FreeResourceService {

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    public FreeResourceServiceImpl(FreeResourceDAO dao) {
        super(dao);
    }

    @Override
    public FreeResource update(FreeResource entity) {
        return dao.update(entity);
    }

    @Override
    public FreeResource create(FreeResource entity) {
        return dao.create(entity);
    }

    @Override
    protected void copyProperties(FreeResource current, FreeResource newEntity) {
        if (!newEntity.getImage().isEmpty()) {
            current.setImage(newEntity.getImage());
        }
        current.setName(newEntity.getName());
        current.setDescription(newEntity.getDescription());
        current.setResource(newEntity.getResource());

        copyCategories(current, newEntity);
    }

    private void copyCategories(FreeResource current, FreeResource newEntity) {
        List<Category> categories = new LinkedList<Category>();
        if (newEntity.getCategories() != null) {
            for (Category category : newEntity.getCategories()) {
                categories.add(categoryDAO.get(category.getId()));
            }
        }

        Iterator<Category> categoryIterator = current.getCategories().iterator();
        while (categoryIterator.hasNext()) {
            Category category = categoryIterator.next();
            if (!categories.contains(category)) {
                categoryIterator.remove();
                category.getResources().remove(current);
                categoryDAO.update(category);
            } else {
                categories.remove(category);
            }
        }

        for (Category category : categories) {
            current.addCategory(category);
        }
    }
}
