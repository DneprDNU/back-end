package org.dnu.filestorage.data.dao.impl;

import org.dnu.filestorage.data.dao.TeacherDAO;
import org.dnu.filestorage.data.model.Teacher;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author demyura
 * @since 14.11.14
 */
@Repository
@Transactional
public class TeacherDAOImpl extends GenericDAOImpl<Teacher> implements TeacherDAO {

    @Override
    public List<Teacher> getTeachersBySpecialityId(Long specialityId) {
        return null;
    }

    @Override
    public List<Teacher> getTeachersByDepartmentId(Long departmentId) {
        return null;
    }

    @Override
    public Teacher get(Object id) {
        Teacher result = super.get(id);
        result.getDepartments().size();
        result.getSpecialities().size();
        return result;
    }
}
