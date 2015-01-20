package org.dnu.filestorage.data.dao;

import org.dnu.filestorage.data.model.Faculty;

/**
 * @author demyura
 * @since 15.10.14
 */
public interface FacultyDAO extends GenericDAO<Faculty> {
    Faculty getFacultyWithRelations(Long id);
}
