package org.dnu.filestorage.service.dao;

import org.dnu.filestorage.model.Speciality;

import java.util.List;

/**
 * @author demyura
 * @since 15.10.14
 */
public interface SpecialityDAO extends GenericDAO<Speciality> {
    List<Speciality> getSpecialitiesByFacultyId(Long facultyId);
}
