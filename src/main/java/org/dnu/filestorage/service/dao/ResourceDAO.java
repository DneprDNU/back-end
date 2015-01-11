package org.dnu.filestorage.service.dao;

import org.dnu.filestorage.model.Resource;

import java.util.List;

/**
 * @author demyura
 * @since 15.10.14
 */
public interface ResourceDAO extends GenericDAO<Resource> {

    List<Resource> listByCategoryId(Long categoryId);

    List<Resource> listResourcesByTeacherIdByLinks(Long teacherId);
}
