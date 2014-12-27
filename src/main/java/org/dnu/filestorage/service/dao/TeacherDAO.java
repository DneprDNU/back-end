package org.dnu.filestorage.service.dao;

import org.dnu.filestorage.model.Teacher;

import java.util.List;

/**
 * @author demyura
 * @since 15.10.14
 */
public interface TeacherDAO extends GenericDAO<Teacher> {
    List<Teacher> getTeachersBySpecialityId(Long specialityId);

    List<Teacher> getTeachersByDepartmentId(Long departmentId);
}
