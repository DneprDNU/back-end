package org.dnu.filestorage.service.dao.impl;

import org.dnu.filestorage.model.Teacher;
import org.dnu.filestorage.service.dao.TeacherDAO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author demyura
 * @since 14.11.14
 */
@Repository
@Transactional
public class TeacherDAOImpl extends GenericDAOImpl<Teacher> implements TeacherDAO {

}
