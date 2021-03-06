package org.dnu.filestorage.data.dao.impl;

import org.dnu.filestorage.data.dao.SpecialityDAO;
import org.dnu.filestorage.data.model.Speciality;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author demyura
 * @since 14.11.14
 */
@Repository
@Transactional
public class SpecialityDAOImpl extends GenericDAOImpl<Speciality> implements SpecialityDAO {

    @Override
    public List<Speciality> getSpecialitiesByFacultyId(Long facultyId) {
//        CriteriaBuilder builder=entityManager.getCriteriaBuilder();
//        builder.
        return entityManager.createNamedQuery("Speciality.getSpecialitiesByFacultyId", Speciality.class).setParameter("facultyId", facultyId)
                .getResultList();
    }

    @Override
    public Speciality get(Object id) {
        Speciality result = super.get(id);
        result.getDepartments().size();
        result.getSupervisors().size();
        return result;
    }

    @Override
    public List<Speciality> listByFacultyId(long facultyId) {
        return getSpecialitiesByFacultyId(facultyId);
    }

    @Override
    public List<Speciality> listByFacultyId(long facultyId, int from, int to) {
        return entityManager.createNamedQuery("Speciality.getSpecialitiesByFacultyId", Speciality.class).setParameter("facultyId", facultyId)
                .setFirstResult(from).setMaxResults(to - from).getResultList();
    }

    @Override
    public long filteredCount(long facultyId) {
        return (long) entityManager.createNamedQuery("Speciality.filteredCount")
                .setParameter("facultyId", facultyId).getSingleResult();
    }
}
