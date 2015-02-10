package org.dnu.filestorage.data.dao.impl;

import org.dnu.filestorage.data.dao.CategoryDAO;
import org.dnu.filestorage.data.model.Category;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author demyura
 * @since 14.11.14
 */
@Repository
@Transactional
public class CategoryDAOImpl extends GenericDAOImpl<Category> implements CategoryDAO {
    @Override
    public Category get(Object id) {
        Category result = super.get(id);
        result.setResourceCount(result.getResources().size());
        return result;
    }

    @Override
    public List<Category> list() {
        List<Category> result = super.list();
        for (Category category : result) {
            category.setResourceCount(category.getResources().size());
        }
        return result;
    }
}
