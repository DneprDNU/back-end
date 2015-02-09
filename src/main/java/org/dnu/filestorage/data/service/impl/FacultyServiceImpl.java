package org.dnu.filestorage.data.service.impl;

import org.dnu.filestorage.data.dao.DepartmentDAO;
import org.dnu.filestorage.data.dao.FacultyDAO;
import org.dnu.filestorage.data.model.Department;
import org.dnu.filestorage.data.model.Faculty;
import org.dnu.filestorage.data.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author demyura
 * @since 14.11.14
 */
@Service
@Transactional
public class FacultyServiceImpl extends GenericServiceImpl<FacultyDAO, Faculty> implements FacultyService {

    @Autowired
    private DepartmentDAO departmentDAO;

    @Autowired
    public FacultyServiceImpl(FacultyDAO dao) {
        super(dao);
    }

    @Override
    public Faculty get(Long id) {
        return dao.getFacultyWithRelations(id);
    }

    @Override
    protected void copyProperties(Faculty current, Faculty newEntity) {
        List<Department> departmentList = new ArrayList<Department>();
        for (Department department : newEntity.getDepartments()) {
            Long departmentId = department.getId();
            Department departmentFromDatabase = departmentDAO.get(departmentId);
            departmentList.add(departmentFromDatabase);
        }

        current.setDepartments(departmentList);
    }
}
