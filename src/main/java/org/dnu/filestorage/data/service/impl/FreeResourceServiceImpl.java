package org.dnu.filestorage.data.service.impl;

import org.dnu.filestorage.data.dao.FreeResourceCategoryDAO;
import org.dnu.filestorage.data.dao.FreeResourceDAO;
import org.dnu.filestorage.data.model.FreeResource;
import org.dnu.filestorage.data.model.FreeResourceCategory;
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
    private FreeResourceCategoryDAO categoryDAO;

    @Autowired
    public FreeResourceServiceImpl(FreeResourceDAO dao) {
        super(dao);
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
        List<FreeResourceCategory> categories = new LinkedList<FreeResourceCategory>();
        if (newEntity.getCategories() != null) {
            for (FreeResourceCategory category : newEntity.getCategories()) {
                categories.add(categoryDAO.get(category.getId()));
            }
        }

        Iterator<FreeResourceCategory> categoryIterator = current.getCategories().iterator();
        while (categoryIterator.hasNext()) {
            FreeResourceCategory category = categoryIterator.next();
            if (!categories.contains(category)) {
                categoryIterator.remove();
                category.getFreeResources().remove(current);
                categoryDAO.update(category);
            } else {
                categories.remove(category);
            }
        }

        for (FreeResourceCategory category : categories) {
            current.addCategory(category);
        }
    }
}
