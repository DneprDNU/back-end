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
import java.util.Iterator;
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
        current.setName(newEntity.getName());
        if (!newEntity.getImage().isEmpty()) {
            current.setImage(newEntity.getImage());
        }
        current.setDescription(newEntity.getDescription());
        current.setShortName(newEntity.getShortName());

        List<Department> departmentList = new ArrayList<Department>();
        for (Department department : newEntity.getDepartments()) {
            Long departmentId = department.getId();
            Department departmentFromDatabase = departmentDAO.get(departmentId);
            departmentList.add(departmentFromDatabase);
        }

        Iterator<Department> departmentIterator = current.getDepartments().iterator();
        while (departmentIterator.hasNext()) {
            Department nextDepartment = departmentIterator.next();
            if (!departmentList.contains(nextDepartment)) {
                nextDepartment.setFaculty(null);
                departmentIterator.remove();
                departmentDAO.update(nextDepartment);
            } else {
                departmentList.remove(nextDepartment);
            }
        }

        for (Department department : departmentList) {
            current.addDepartment(department);
        }
    }

    @Override
    public List<Faculty> listByFacultyId(long facultyId) {
        return dao.listByFacultyId(facultyId);
    }
}
