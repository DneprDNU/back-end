package org.dnu.filestorage.data.service.impl;

import org.dnu.filestorage.data.dao.FilteredDAO;
import org.dnu.filestorage.data.dao.GenericDAO;
import org.dnu.filestorage.data.model.Identifiable;
import org.dnu.filestorage.data.service.FilteredService;

import java.util.List;

/**
 * @author demyura
 * @since 21.04.15
 */
public abstract class GenericFilteredService<D extends GenericDAO<T>, T extends Identifiable>
        extends GenericServiceImpl<D, T> implements FilteredService<T> {
    public GenericFilteredService(D dao) {
        super(dao);
    }

    @Override
    public List<T> listByFacultyId(long facultyId) {
        return ((FilteredDAO<T>) dao).listByFacultyId(facultyId);
    }

    @Override
    public List<T> listByFacultyId(long facultyId, int from, int to) {
        return ((FilteredDAO<T>) dao).listByFacultyId(facultyId, from, to);
    }
}
