package org.dnu.filestorage.service.dao.impl;

import org.dnu.filestorage.model.Subject;
import org.dnu.filestorage.service.dao.SubjectDAO;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @author demyura
 * @since 14.11.14
 */
@Repository
@Transactional
public class SubjectDAOImpl extends GenericDAOImpl<Subject> implements SubjectDAO {

}
