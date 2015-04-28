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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author demyura
 * @since 14.11.14
 */
@Service
@Transactional
public class ResourceServiceImpl extends GenericFilteredService<ResourceDAO, Resource> implements ResourceService {

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
        return dao.listResourcesByTeacherIdByLinks(teacherId);
    }

    @Override
    public void updateDownloads(String filename) {
        dao.updateDownloads(filename);
    }

    @Override
    public Resource get(Long id) {
        return dao.get(id);
    }

    private void copySubjects(Resource current, Resource newEntity) {
        List<Subject> subjects = new LinkedList<Subject>();
        if (newEntity.getSubjects() != null) {
            for (Subject subject : newEntity.getSubjects()) {
                subjects.add(subjectDAO.get(subject.getId()));
            }
        }

        Iterator<Subject> subjectIterator = current.getSubjects().iterator();
        while (subjectIterator.hasNext()) {
            Subject subject = subjectIterator.next();
            if (!subjects.contains(subject)) {
                subjectIterator.remove();
                subject.getResources().remove(current);
                subjectDAO.update(subject);
            } else {
                subjects.remove(subject);
            }
        }

        for (Subject subject : subjects) {
            current.addSubject(subject);
        }
    }

    private void copyCategories(Resource current, Resource newEntity) {
        List<Category> categories = new LinkedList<Category>();
        if (newEntity.getCategories() != null) {
            for (Category category : newEntity.getCategories()) {
                categories.add(categoryDAO.get(category.getId()));
            }
        }

        Iterator<Category> categoryIterator = current.getCategories().iterator();
        while (categoryIterator.hasNext()) {
            Category category = categoryIterator.next();
            if (!categories.contains(category)) {
                categoryIterator.remove();
                category.getResources().remove(current);
                categoryDAO.update(category);
            } else {
                categories.remove(category);
            }
        }

        for (Category category : categories) {
            current.addCategory(category);
        }
    }

    private void copySpeciality(Resource current, Resource newEntity) {
        current.setSpeciality(newEntity.getSpeciality() == null ? null :
                specialityDao.get(newEntity.getSpeciality().getId()));
    }

    @Override
    protected void copyProperties(Resource current, Resource newEntity) {
        if (!newEntity.getImage().isEmpty()) {
            current.setImage(newEntity.getImage());
        }
        current.setName(newEntity.getName());
        current.setAuthor(newEntity.getAuthor());
        current.setDescription(newEntity.getDescription());
        if (!newEntity.getFileR().isEmpty()) {
            current.setFileR(newEntity.getFileR());
        }
        current.setYear(newEntity.getYear());

        copySpeciality(current, newEntity);
        copyCategories(current, newEntity);
        copySubjects(current, newEntity);
    }
}
