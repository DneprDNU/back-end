package org.dnu.filestorage.data.dao;

import org.dnu.filestorage.data.model.Teacher;

import java.util.List;

/**
 * @author demyura
 * @since 15.10.14
 */
public interface TeacherDAO extends GenericDAO<Teacher>, FilteredDAO<Teacher> {
    List<Teacher> getTeachersByFacultyId(Long facultyId);

    List<Teacher> getTeachersBySubjectId(Long subjectId);

    List<Teacher> getTeachersBySpecialityId(Long specialityId);
}
