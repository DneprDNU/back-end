package org.dnu.filestorage.data.service.impl;

import org.dnu.filestorage.data.dao.TeacherDAO;
import org.dnu.filestorage.data.model.Teacher;
import org.dnu.filestorage.data.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author demyura
 * @since 14.11.14
 */
@Service
@Transactional
public class TeacherServiceImpl extends GenericFilteredService<TeacherDAO, Teacher> implements TeacherService {

    @Autowired
    public TeacherServiceImpl(TeacherDAO dao) {
        super(dao);
    }

    @Override
    public List<Teacher> getTeachersBySpecialityId(Long specialityId) {
        return dao.getTeachersBySpecialityId(specialityId);
    }

    @Override
    public List<Teacher> getTeachersByDepartmentId(Long departmentId) {
        return null;
    }

    @Override
    public List<Teacher> getTeachersBySubjectId(Long departmentId) {
        return dao.getTeachersBySubjectId(departmentId);
    }

    @Override
    public Teacher get(Long id) {
        return dao.get(id);
    }

    @Override
    protected void copyProperties(Teacher current, Teacher newEntity) {
        current.setName(newEntity.getName());
        if (!newEntity.getImage().isEmpty()) {
            current.setImage(newEntity.getImage());
        }
    }
}
