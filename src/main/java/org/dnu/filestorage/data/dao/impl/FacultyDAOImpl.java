package org.dnu.filestorage.data.dao.impl;

import org.dnu.filestorage.data.dao.FacultyDAO;
import org.dnu.filestorage.data.dao.FilteredDAO;
import org.dnu.filestorage.data.model.Faculty;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author demyura
 * @since 14.11.14
 */
@Repository
@Transactional
public class FacultyDAOImpl extends GenericDAOImpl<Faculty> implements FacultyDAO, FilteredDAO<Faculty> {

    @Override
    public Faculty getFacultyWithRelations(Long id) {
        return (Faculty) entityManager.createNamedQuery("getFacultyWithRelations").setParameter("facultyId", id)
                .getSingleResult();
    }


    @Override
    public List<Faculty> listByFacultyId(long facultyId) {
        return new LinkedList<Faculty>(Arrays.asList(get(facultyId)));
    }
}
