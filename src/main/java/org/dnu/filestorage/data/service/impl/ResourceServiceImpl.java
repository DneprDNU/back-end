package org.dnu.filestorage.data.service.impl;

import org.dnu.filestorage.data.dao.CategoryDAO;
import org.dnu.filestorage.data.dao.ResourceDAO;
import org.dnu.filestorage.data.dao.SpecialityDAO;
import org.dnu.filestorage.data.dao.SubjectDAO;
import org.dnu.filestorage.data.model.Category;
import org.dnu.filestorage.data.model.Resource;
import org.dnu.filestorage.data.model.Subject;
import org.dnu.filestorage.data.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

/**
 * @author demyura
 * @since 14.11.14
 */
@Service
@Transactional
public class ResourceServiceImpl extends GenericServiceImpl<ResourceDAO, Resource> implements ResourceService {

    @Autowired
    private SpecialityDAO specialityDao;
    @Autowired
    private SubjectDAO subjectDAO;
    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    public ResourceServiceImpl(ResourceDAO dao) {
        super(dao);
    }

    @Override
    public List<Resource> listByCategoryId(Long categoryId) {
        return dao.listByCategoryId(categoryId);
    }

    @Override
    public List<Resource> listResourcesByTeacherIdByLinks(Long teacherId) {
        return listResourcesByTeacherIdByLinks(teacherId);
    }

    @Override
    public Resource get(Long id) {
        return dao.get(id);
    }

    @Override
    public Resource update(Resource entity) {
        Resource current = dao.get(entity.getId());
        copyProperties(current, entity);
        copySpeciality(current, entity);
        copyCategories(current, entity);
        copySubjects(current, entity);
        return dao.update(current);
    }

    private void copySubjects(Resource current, Resource newEntity) {
        List<Subject> subjects = new LinkedList<Subject>();
        if (newEntity.getSubjects() != null) {
            for (Subject subject : newEntity.getSubjects()) {
                subjects.add(subjectDAO.get(subject.getId()));
            }
        }
        current.setSubjects(subjects);
    }

    private void copyCategories(Resource current, Resource newEntity) {
        List<Category> categories = new LinkedList<Category>();
        if (newEntity.getCategories() != null) {
            for (Category category : newEntity.getCategories()) {
                categories.add(categoryDAO.get(category.getId()));
            }
        }
        current.setCategories(categories);
    }

    private void copySpeciality(Resource current, Resource newEntity) {
        current.setSpeciality(newEntity.getSpeciality() == null ? null : specialityDao.get(newEntity.getId()));
    }

    private void copyProperties(Resource current, Resource newEntity) {
        current.setImage(newEntity.getImage());
        current.setName(newEntity.getName());
        current.setAuthor(newEntity.getAuthor());
        current.setDescription(newEntity.getDescription());
        current.setResource(newEntity.getResource());
        current.setYear(newEntity.getYear());
    }
}
