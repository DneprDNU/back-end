package org.dnu.filestorage.data.service.impl;

import org.dnu.filestorage.data.dao.SubjectDAO;
import org.dnu.filestorage.data.model.Subject;
import org.dnu.filestorage.data.service.SubjectService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author demyura
 * @since 14.11.14
 */
@Service
@Transactional
public class SubjectServiceImpl extends GenericServiceImpl<SubjectDAO, Subject> implements SubjectService {

    @Override
    public List<Subject> getByDepartmentId(Long departmentId) {
        return dao.getByDepartmentId(departmentId);
    }

    @Override
    public Subject get(Long id) {
        return dao.get(id);
    }
}
