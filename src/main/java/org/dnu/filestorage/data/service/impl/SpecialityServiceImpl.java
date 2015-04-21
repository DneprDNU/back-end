package org.dnu.filestorage.data.service.impl;

import org.dnu.filestorage.data.dao.SpecialityDAO;
import org.dnu.filestorage.data.dao.TeacherDAO;
import org.dnu.filestorage.data.model.Speciality;
import org.dnu.filestorage.data.model.Teacher;
import org.dnu.filestorage.data.service.SpecialityService;
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
public class SpecialityServiceImpl extends GenericFilteredService<SpecialityDAO, Speciality> implements SpecialityService {

    @Autowired
    private TeacherDAO teacherDao;

    @Autowired
    public SpecialityServiceImpl(SpecialityDAO dao) {
        super(dao);
    }

    @Override
    public List<Speciality> getSpecialitiesByFacultyId(Long facultyId) {
        return dao.getSpecialitiesByFacultyId(facultyId);
    }

    @Override
    protected void copyProperties(Speciality current, Speciality newEntity) {
        copyFields(current, newEntity);
        copySupervisors(current, newEntity);
    }

    private void copySupervisors(Speciality current, Speciality newEntity) {
        List<Teacher> supervisors = new LinkedList<Teacher>();
        if (newEntity.getSupervisors() != null) {
            for (Teacher supervisor : newEntity.getSupervisors()) {
                supervisors.add(teacherDao.get(supervisor.getId()));
            }
        }

        Iterator<Teacher> teacherIterator = current.getSupervisors().iterator();
        while (teacherIterator.hasNext()) {
            Teacher teacher = teacherIterator.next();
            if (!supervisors.contains(teacher)) {
                teacherIterator.remove();
                teacher.getSpecialities().remove(current);
                teacherDao.update(teacher);
            } else {
                supervisors.remove(teacher);
            }
        }

        for (Teacher teacher : supervisors) {
            current.addSupervisor(teacher);
        }
    }

    private void copyFields(Speciality current, Speciality newEntity) {
        if (!newEntity.getImage().isEmpty()) {
            current.setImage(newEntity.getImage());
        }
        current.setName(newEntity.getName());
        current.setCode(newEntity.getCode());
    }

    @Override
    public Speciality get(Long id) {
        return dao.get(id);
    }
}
