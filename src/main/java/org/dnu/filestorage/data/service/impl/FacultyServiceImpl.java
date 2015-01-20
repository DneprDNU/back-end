package org.dnu.filestorage.data.service.impl;

import org.dnu.filestorage.data.dao.FacultyDAO;
import org.dnu.filestorage.data.model.Faculty;
import org.dnu.filestorage.data.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author demyura
 * @since 14.11.14
 */
@Service
@Transactional
public class FacultyServiceImpl extends GenericServiceImpl<FacultyDAO, Faculty> implements FacultyService {

    @Autowired
    public FacultyServiceImpl(FacultyDAO dao) {
        super(dao);
    }

    @Override
    public Faculty get(Long id) {
        return dao.getFacultyWithRelations(id);
    }
}
