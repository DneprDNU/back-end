package org.dnu.filestorage.service.dao.impl;

import org.dnu.filestorage.model.Resource;
import org.dnu.filestorage.service.dao.ResourceDAO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author demyura
 * @since 14.11.14
 */
@Repository
@Transactional
public class ResourceDAOImpl extends GenericDAOImpl<Resource> implements ResourceDAO {

}
