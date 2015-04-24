package org.dnu.filestorage.data.dao.impl;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@subjectId")
public class SubjectDAOImpl extends GenericDAOImpl<Subject> implements SubjectDAO {

    @Override
    public List<Subject> getByDepartmentId(Long departmentId) {
        return entityManager.createNamedQuery("getSubjectsByDepartmentIdByLinks", Subject.class)
                .setParameter("departmentId", departmentId).getResultList();
    }

    @Override
    public List<Subject> getBySpecialityId(Long specialityId) {
        return entityManager.createNamedQuery("getSubjectsBySpecialityIdByLinks", Subject.class)
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
        return entityManager.createNamedQuery("listSubjectsByFacultyId", Subject.class)
                .setParameter("facultyId", facultyId).getResultList();
    }

    @Override
    public List<Subject> listByFacultyId(long facultyId, int from, int to) {
        return entityManager.createNamedQuery("listSubjectsByFacultyId", Subject.class)
                .setParameter("facultyId", facultyId).setFirstResult(from).setMaxResults(to - from).getResultList();
    }
}
