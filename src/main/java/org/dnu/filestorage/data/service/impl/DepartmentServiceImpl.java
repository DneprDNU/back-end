package org.dnu.filestorage.data.service.impl;

import org.dnu.filestorage.data.dao.DepartmentDAO;
import org.dnu.filestorage.data.dao.SpecialityDAO;
import org.dnu.filestorage.data.dao.TeacherDAO;
import org.dnu.filestorage.data.model.Department;
import org.dnu.filestorage.data.model.Speciality;
import org.dnu.filestorage.data.model.Teacher;
import org.dnu.filestorage.data.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

/**
 * @author demyura
 * @since 14.11.14
 */
@Service
@Transactional
public class DepartmentServiceImpl extends GenericServiceImpl<DepartmentDAO, Department> implements DepartmentService {
    @Autowired
    private SpecialityDAO specialityDAO;
    @Autowired
    private TeacherDAO teacherDAO;

    @Autowired
    public DepartmentServiceImpl(DepartmentDAO dao) {
        super(dao);
    }

    @Override
    public Department get(Long id) {
        return dao.getDepartmentWithRelations(id);
    }

    @Override
    public Department update(Department newEntity) {
        Long id = newEntity.getId();

        Department current = dao.get(id);
        copyProperties(newEntity, current);
        updateSpecialities(newEntity, current);
        updateEmployees(newEntity, current);

        return dao.update(current);
    }

    @Override
    public Department create(Department newEntity) {
        Department department = dao.create(new Department());
        newEntity.setId(department.getId());
        update(newEntity);
        return department;
    }

    private void copyProperties(Department newEntity, Department current) {
        current.setName(newEntity.getName());
        current.setShortName(newEntity.getShortName());
        current.setImage(newEntity.getImage());
    }

    private void updateEmployees(Department newEntity, Department current) {
        if (newEntity.getEmployees() != null) {
            List<Teacher> newEmployees = new LinkedList<Teacher>();
            for (Teacher teacher : newEntity.getEmployees()) {
                newEmployees.add(teacherDAO.get(teacher.getId()));
            }
            current.setEmployees(newEmployees);
        }
    }

    private void updateSpecialities(Department newEntity, Department current) {
        if (newEntity.getSpecialities() != null) {
            List<Speciality> newSpecialities = new LinkedList<Speciality>();
            for (Speciality speciality : newEntity.getSpecialities()) {
                newSpecialities.add(specialityDAO.get(speciality.getId()));
            }
            current.setSpecialities(newSpecialities);
        }
    }
}
