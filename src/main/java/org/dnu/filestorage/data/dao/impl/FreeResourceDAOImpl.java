package org.dnu.filestorage.data.dao.impl;

import org.dnu.filestorage.data.dao.FreeResourceDAO;
import org.dnu.filestorage.data.model.FreeResource;
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
