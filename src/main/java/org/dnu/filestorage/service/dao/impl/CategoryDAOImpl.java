package org.dnu.filestorage.service.dao.impl;

import org.dnu.filestorage.model.Category;
import org.dnu.filestorage.service.dao.CategoryDAO;
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
