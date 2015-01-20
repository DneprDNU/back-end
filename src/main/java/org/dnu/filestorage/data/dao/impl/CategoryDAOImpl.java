package org.dnu.filestorage.data.dao.impl;

import org.dnu.filestorage.data.dao.CategoryDAO;
import org.dnu.filestorage.data.model.Category;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @author demyura
 * @since 14.11.14
 */
@Repository
@Transactional
public class CategoryDAOImpl extends GenericDAOImpl<Category> implements CategoryDAO {

}
