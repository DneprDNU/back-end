package org.dnu.filestorage.data.dao.impl;

import org.dnu.filestorage.data.dao.ResourceDAO;
import org.dnu.filestorage.data.model.Resource;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author demyura
 * @since 14.11.14
 */
@Repository
@Transactional
public class ResourceDAOImpl extends GenericDAOImpl<Resource> implements ResourceDAO {

    @Override
    public List<Resource> listByCategoryId(Long categoryId) {
        return entityManager.createNamedQuery("Resource.getResourcesByCategoryId", Resource.class)
                .setParameter("categoryId", categoryId).getResultList();
    }

    @Override
    public List<Resource> listResourcesByTeacherIdByLinks(Long teacherId) {
        return entityManager.createNamedQuery("Resource.getResourcesByTeacherIdByLinks", Resource.class)
                .setParameter("teacherId", teacherId).getResultList();
    }

    @Override
    public void updateDownloads(String filename) {
        entityManager.createNamedQuery("Resource.addDownload")
                .setParameter("fileName", filename).executeUpdate();
    }

    @Override
    public Resource get(Object id) {
        return entityManager.createNamedQuery("Resource.loadWithRelations", Resource.class)
                .setParameter("resourceId", id).getSingleResult();
    }

    @Override
    public List<Resource> listByFacultyId(long facultyId) {
        return entityManager.createNamedQuery("Resource.listResourcesByFaculty", Resource.class)
                .setParameter("facultyId", facultyId).getResultList();
    }

    @Override
    public List<Resource> listByFacultyId(long facultyId, int from, int to) {
        return entityManager.createNamedQuery("Resource.listResourcesByFaculty", Resource.class)
                .setParameter("facultyId", facultyId).setFirstResult(from).setMaxResults(to - from).getResultList();
    }

    @Override
    public long filteredCount(long facultyId) {
        return (long) entityManager.createNamedQuery("Resource.filteredCount")
                .setParameter("facultyId", facultyId).getSingleResult();
    }
}
