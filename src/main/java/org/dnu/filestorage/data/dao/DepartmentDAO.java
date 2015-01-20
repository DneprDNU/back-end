package org.dnu.filestorage.data.dao;

import org.dnu.filestorage.data.model.Department;

/**
 * @author demyura
 * @since 15.10.14
 */
public interface DepartmentDAO extends GenericDAO<Department> {
    Department getDepartmentWithRelations(Long id);
}
