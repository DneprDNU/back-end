package org.dnu.filestorage.data.service.impl;

import org.dnu.filestorage.data.dao.SpecialityDAO;
import org.dnu.filestorage.data.model.Speciality;
import org.dnu.filestorage.data.service.SpecialityService;
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
public class SpecialityServiceImpl extends GenericServiceImpl<SpecialityDAO, Speciality> implements SpecialityService {

    @Autowired
    public SpecialityServiceImpl(SpecialityDAO dao) {
        super(dao);
    }

    @Override
    public List<Speciality> getSpecialitiesByFacultyId(Long facultyId) {
        return dao.getSpecialitiesByFacultyId(facultyId);
    }

    @Override
    public Speciality get(Long id) {
        return dao.get(id);
    }
}
