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
import java.util.Iterator;
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
    protected void copyProperties(Department current, Department newEntity ) {
        current.setName(newEntity.getName());
        current.setShortName(newEntity.getShortName());
        if (!newEntity.getImage().isEmpty()) {
            current.setImage(newEntity.getImage());
        }

        updateSpecialities(newEntity, current);
        updateEmployees(newEntity, current);
    }

    private void updateEmployees(Department newEntity, Department current) {
        List<Teacher> newEmployees = new LinkedList<Teacher>();
        if (newEntity.getEmployees() != null) {
            for (Teacher teacher : newEntity.getEmployees()) {
                newEmployees.add(teacherDAO.get(teacher.getId()));
            }
        }

        Iterator<Teacher> teacherIterator = current.getEmployees().iterator();
        while (teacherIterator.hasNext()) {
            Teacher teacher = teacherIterator.next();
            if (!newEmployees.contains(teacher)) {
                teacherIterator.remove();
                teacher.getDepartments().remove(current);
                teacherDAO.update(teacher);
            } else {
                newEmployees.remove(teacher);
            }
        }

        for (Teacher teacher : newEmployees) {
            current.addEmployee(teacher);
        }
    }

    private void updateSpecialities(Department newEntity, Department current) {
        List<Speciality> newSpecialities = new LinkedList<Speciality>();
        if (newEntity.getSpecialities() != null) {
            for (Speciality speciality : newEntity.getSpecialities()) {
                newSpecialities.add(specialityDAO.get(speciality.getId()));
            }
        }

        Iterator<Speciality> specialityIterator = current.getSpecialities().iterator();
        while (specialityIterator.hasNext()) {
            Speciality speciality = specialityIterator.next();
            if (!newSpecialities.contains(speciality)) {
                specialityIterator.remove();
                speciality.getDepartments().remove(current);
                specialityDAO.update(speciality);
            } else {
                newSpecialities.remove(speciality);
            }
        }

        for (Speciality speciality : newSpecialities) {
            current.addSpeciality(speciality);
        }
    }
}
