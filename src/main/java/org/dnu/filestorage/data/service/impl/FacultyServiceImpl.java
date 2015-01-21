package org.dnu.filestorage.data.service.impl;

import org.dnu.filestorage.data.dao.DepartmentDAO;
import org.dnu.filestorage.data.dao.FacultyDAO;
import org.dnu.filestorage.data.model.Department;
import org.dnu.filestorage.data.model.Faculty;
import org.dnu.filestorage.data.model.Speciality;
import org.dnu.filestorage.data.model.Subject;
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
    public Faculty update(Faculty entity) {
        List<Department> departmentList = new ArrayList<Department>();
        for (Department department: entity.getDepartments()){
            Long departmentId = department.getId();
            Department departmentFromDatabase = departmentDAO.get(departmentId);
            departmentList.add(departmentFromDatabase);
        }

        entity.setDepartments(departmentList);
        return dao.update(entity);

    }

    @Override
    public Faculty create(Faculty entity) {
        Faculty faculty = dao.create(new Faculty());
        faculty.setId(entity.getId());
        return update(faculty);
    }
}
