package org.dnu.filestorage.service.dao.impl;

import org.dnu.filestorage.model.Faculty;
import org.dnu.filestorage.service.dao.FacultyDAO;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @author demyura
 * @since 14.11.14
 */
@Repository
@Transactional
public class FacultyDAOImpl extends GenericDAOImpl<Faculty> implements FacultyDAO {

}
