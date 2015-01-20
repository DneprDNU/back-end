package org.dnu.filestorage.data.service;

import org.dnu.filestorage.data.model.Resource;

import java.util.List;

/**
 * @author demyura
 * @since 15.10.14
 */
public interface ResourceService extends GenericService<Resource> {

    List<Resource> listByCategoryId(Long categoryId);

    List<Resource> listResourcesByTeacherIdByLinks(Long teacherId);
}
