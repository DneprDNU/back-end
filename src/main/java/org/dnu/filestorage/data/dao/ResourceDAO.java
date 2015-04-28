package org.dnu.filestorage.data.dao;

import org.dnu.filestorage.data.model.Resource;

import java.util.List;

/**
 * @author demyura
 * @since 15.10.14
 */
public interface ResourceDAO extends GenericDAO<Resource>, FilteredDAO<Resource> {

    List<Resource> listByCategoryId(Long categoryId);

    List<Resource> listResourcesByTeacherIdByLinks(Long teacherId);

    void updateDownloads(String filename);
}
