package org.dnu.filestorage.service.dao;

import org.dnu.filestorage.model.Subject;

import java.util.List;

/**
 * @author demyura
 * @since 15.10.14
 */
public interface SubjectDAO extends GenericDAO<Subject> {

    List<Subject> getByDepartmentId(Long departmentId);
}
