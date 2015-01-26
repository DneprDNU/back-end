package org.dnu.filestorage.data.dao.impl;

import org.dnu.filestorage.data.dao.FreeResourceCategoryDAO;
import org.dnu.filestorage.data.model.FreeResourceCategory;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @author demyura
 * @since 14.11.14
 */
@Repository
@Transactional
public class FreeResourceCategoryDAOImpl extends GenericDAOImpl<FreeResourceCategory> implements FreeResourceCategoryDAO {

}
