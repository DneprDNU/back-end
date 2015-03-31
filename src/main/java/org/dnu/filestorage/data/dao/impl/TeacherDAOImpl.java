package org.dnu.filestorage.data.dao.impl;

import org.dnu.filestorage.data.dao.FilteredDAO;
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
public class TeacherDAOImpl extends GenericDAOImpl<Teacher> implements TeacherDAO, FilteredDAO<Teacher> {


    @Override
    public Teacher get(Object id) {
        Teacher result = super.get(id);
        result.getDepartments().size();
        result.getSpecialities().size();
        return result;
    }

    @Override
    public List<Teacher> getTeachersByFacultyId(Long facultyId) {
        return entityManager.createNamedQuery("listTeachersByFacultyId", Teacher.class)
                .setParameter("facultyId", facultyId).getResultList();
    }

    @Override
    public List<Teacher> listByFacultyId(long facultyId) {
        return getTeachersByFacultyId(facultyId);
    }
}
