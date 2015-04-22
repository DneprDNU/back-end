package org.dnu.filestorage.data.dao;

import org.dnu.filestorage.data.model.Subject;

import java.util.List;

/**
 * @author demyura
 * @since 15.10.14
 */
public interface SubjectDAO extends GenericDAO<Subject>, FilteredDAO<Subject> {

    List<Subject> getByDepartmentId(Long departmentId);

    List<Subject> getBySpecialityId(Long specialityId);
}
