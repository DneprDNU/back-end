package org.dnu.filestorage.data.service;

import org.dnu.filestorage.data.model.Teacher;

import java.util.List;

/**
 * @author demyura
 * @since 15.10.14
 */
public interface TeacherService extends GenericService<Teacher>, FilteredService<Teacher> {
    List<Teacher> getTeachersBySpecialityId(Long specialityId);

    List<Teacher> getTeachersByDepartmentId(Long departmentId);

    List<Teacher> getTeachersBySubjectId(Long departmentId);
}
