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
    public List<Teacher> getTeachersBySubjectId(Long subjectId) {
        return entityManager.createNamedQuery("listTeachersBySubjectId", Teacher.class)
                .setParameter("subjectId", subjectId).getResultList();
    }

    @Override
    public List<Teacher> getTeachersBySpecialityId(Long specialityId) {
        return entityManager.createNamedQuery("listTeachersBySpecialityIdByLinks", Teacher.class)
                .setParameter("specialityId", specialityId).getResultList();
    }

    @Override
    public List<Teacher> listByFacultyId(long facultyId) {
        return getTeachersByFacultyId(facultyId);
    }

    @Override
    public List<Teacher> listByFacultyId(long facultyId, int from, int to) {
        return entityManager.createNamedQuery("listTeachersByFacultyId", Teacher.class)
                .setParameter("facultyId", facultyId).setFirstResult(from).setMaxResults(to - from).getResultList();
    }
}
