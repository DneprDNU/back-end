package org.dnu.filestorage.data.dao.impl;

import org.dnu.filestorage.data.dao.DepartmentDAO;
import org.dnu.filestorage.data.model.Department;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @author demyura
 * @since 14.11.14
 */
@Repository
@Transactional
public class DepartmentDAOImpl extends GenericDAOImpl<Department> implements DepartmentDAO {

    @Override
    public Department getDepartmentWithRelations(Long id) {
        Department result = (Department) entityManager.createNamedQuery("getDepartmentWithRelations")
                .setParameter("departmentId", id).getSingleResult();
        result.getEmployees().size(); // workaround for JPA problem with multiple join fetch in named query
        return result;
    }
}
