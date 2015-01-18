package org.dnu.filestorage.service.dao.impl;

import org.dnu.filestorage.model.FreeResource;
import org.dnu.filestorage.service.dao.FreeResourceDAO;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @author demyura
 * @since 14.11.14
 */
@Repository
@Transactional
public class FreeResourceDAOImpl extends GenericDAOImpl<FreeResource> implements FreeResourceDAO {

}
