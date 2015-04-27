package org.dnu.filestorage.data.dao.impl;

import org.dnu.filestorage.data.dao.SubjectDAO;
import org.dnu.filestorage.data.model.Subject;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author demyura
 * @since 14.11.14
 */
@Repository
@Transactional
public class SubjectDAOImpl extends GenericDAOImpl<Subject> implements SubjectDAO {

    @Override
    public List<Subject> getByDepartmentId(Long departmentId) {
        return entityManager.createNamedQuery("Subject.getSubjectsByDepartmentIdByLinks", Subject.class)
                .setParameter("departmentId", departmentId).getResultList();
    }

    @Override
    public List<Subject> getBySpecialityId(Long specialityId) {
        return entityManager.createNamedQuery("Subject.getSubjectsBySpecialityIdByLinks", Subject.class)
                .setParameter("specialityId", specialityId).getResultList();
    }

    @Override
    public Subject get(Object id) {
        Subject result = super.get(id);
        result.getResources().size();
        return result;
    }

    @Override
    public List<Subject> listByFacultyId(long facultyId) {
        return entityManager.createNamedQuery("Subject.listSubjectsByFacultyId", Subject.class)
                .setParameter("facultyId", facultyId).getResultList();
    }

    @Override
    public List<Subject> listByFacultyId(long facultyId, int from, int to) {
        return entityManager.createNamedQuery("Subject.listSubjectsByFacultyId", Subject.class)
                .setParameter("facultyId", facultyId).setFirstResult(from).setMaxResults(to - from).getResultList();
    }

    @Override
    public long filteredCount(long facultyId) {
        return (long) entityManager.createNamedQuery("Subject.filteredCount")
                .setParameter("facultyId", facultyId).getSingleResult();
    }
}
