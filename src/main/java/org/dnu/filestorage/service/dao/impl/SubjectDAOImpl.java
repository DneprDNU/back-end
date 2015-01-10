package org.dnu.filestorage.service.dao.impl;

import org.dnu.filestorage.model.Subject;
import org.dnu.filestorage.service.dao.SubjectDAO;
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
    public List<Subject> getBySpecialityId(Long specialityId) {
        return null;
    }

    @Override
    public List<Subject> getByDepartmentId(Long departmentId) {
        return null;
    }

    @Override
    public Subject get(Object id) {
        Subject result = super.get(id);
        result.getResources().size();
        return result;
    }
}
