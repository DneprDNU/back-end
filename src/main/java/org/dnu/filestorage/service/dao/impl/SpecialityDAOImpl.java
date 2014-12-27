package org.dnu.filestorage.service.dao.impl;

import org.dnu.filestorage.model.Speciality;
import org.dnu.filestorage.service.dao.SpecialityDAO;
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
        return entityManager.createNamedQuery("getSpecialitiesByFacultyId").setParameter("facultyId", facultyId)
                .getResultList();
    }
}
