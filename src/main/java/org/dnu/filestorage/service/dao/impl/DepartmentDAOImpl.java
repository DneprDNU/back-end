package org.dnu.filestorage.service.dao.impl;

import org.dnu.filestorage.model.Department;
import org.dnu.filestorage.service.dao.DepartmentDAO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author demyura
 * @since 14.11.14
 */
@Repository
@Transactional
public class DepartmentDAOImpl extends GenericDAOImpl<Department> implements DepartmentDAO {

}
