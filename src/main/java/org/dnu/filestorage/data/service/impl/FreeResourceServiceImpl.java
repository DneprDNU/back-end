package org.dnu.filestorage.data.service.impl;

import org.dnu.filestorage.data.dao.FreeResourceDAO;
import org.dnu.filestorage.data.model.FreeResource;
import org.dnu.filestorage.data.service.FreeResourceService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author demyura
 * @since 14.11.14
 */
@Service
@Transactional
public class FreeResourceServiceImpl extends GenericServiceImpl<FreeResourceDAO, FreeResource> implements FreeResourceService {

}
