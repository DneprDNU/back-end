package org.dnu.filestorage.data.service;

import org.dnu.filestorage.data.model.Speciality;

import java.util.List;

/**
 * @author demyura
 * @since 15.10.14
 */
public interface SpecialityService extends GenericService<Speciality>, FilteredService<Speciality> {
    List<Speciality> getSpecialitiesByFacultyId(Long facultyId);
}
