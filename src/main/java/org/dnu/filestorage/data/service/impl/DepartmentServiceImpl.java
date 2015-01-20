package org.dnu.filestorage.data.service.impl;

import org.dnu.filestorage.data.dao.DepartmentDAO;
import org.dnu.filestorage.data.model.Department;
import org.dnu.filestorage.data.service.DepartmentService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author demyura
 * @since 14.11.14
 */
@Service
@Transactional
public class DepartmentServiceImpl extends GenericServiceImpl<DepartmentDAO, Department> implements DepartmentService {

    @Override
    public Department get(Long id) {
        return dao.getDepartmentWithRelations(id);
    }
}
