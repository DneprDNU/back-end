package org.dnu.filestorage.data.dao.impl;

import org.dnu.filestorage.data.dao.FilteredDAO;
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
public class SubjectDAOImpl extends GenericDAOImpl<Subject> implements SubjectDAO, FilteredDAO<Subject> {

    @Override
    public List<Subject> getByDepartmentId(Long departmentId) {
        return entityManager.createNamedQuery("getSubjectsByDepartmentIdByLinks", Subject.class)
                .setParameter("departmentId", departmentId).getResultList();
    }

    @Override
    public Subject get(Object id) {
        Subject result = super.get(id);
        result.getResources().size();
        return result;
    }

    @Override
    public List<Subject> listByFacultyId(long facultyId) {
        return entityManager.createNamedQuery("listSubjectsByFacultyId", Subject.class)
                .setParameter("facultyId", facultyId).getResultList();
    }
}
