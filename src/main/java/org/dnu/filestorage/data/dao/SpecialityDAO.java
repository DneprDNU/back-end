package org.dnu.filestorage.data.dao;

import org.dnu.filestorage.data.model.Speciality;

import java.util.List;

/**
 * @author demyura
 * @since 15.10.14
 */
public interface SpecialityDAO extends GenericDAO<Speciality>, FilteredDAO<Speciality> {
    List<Speciality> getSpecialitiesByFacultyId(Long facultyId);
}
