package org.dnu.filestorage.data.service;

import org.dnu.filestorage.data.model.Subject;

import java.util.List;

/**
 * @author demyura
 * @since 15.10.14
 */
public interface SubjectService extends GenericService<Subject>, FilteredService<Subject> {

    List<Subject> getByDepartmentId(Long departmentId);

    List<Subject> listBySpecialityId(Long specialityId);
}
