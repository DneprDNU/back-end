package org.dnu.filestorage.data.service;

import org.dnu.filestorage.data.model.Identifiable;

import java.util.List;

/**
 * @author demyura
 * @since 31.03.15
 */
public interface FilteredService<T extends Identifiable> {
    List<T> listByFacultyId(long facultyId);

    List<T> listByFacultyId(long facultyId, int from, int to);
}
