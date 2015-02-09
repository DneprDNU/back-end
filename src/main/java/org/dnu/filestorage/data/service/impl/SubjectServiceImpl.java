package org.dnu.filestorage.data.service.impl;

import org.dnu.filestorage.data.dao.ResourceDAO;
import org.dnu.filestorage.data.dao.SubjectDAO;
import org.dnu.filestorage.data.model.Resource;
import org.dnu.filestorage.data.model.Subject;
import org.dnu.filestorage.data.service.SubjectService;
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
public class SubjectServiceImpl extends GenericServiceImpl<SubjectDAO, Subject> implements SubjectService {
    @Autowired
    private ResourceDAO resourceDao;

    @Autowired
    public SubjectServiceImpl(SubjectDAO dao) {
        super(dao);
    }

    @Override
    public List<Subject> getByDepartmentId(Long departmentId) {
        return dao.getByDepartmentId(departmentId);
    }

    @Override
    public Subject get(Long id) {
        return dao.get(id);
    }

    private void copyResources(Subject current, Subject newEntity) {
        List<Resource> resources = new LinkedList<Resource>();
        if (newEntity.getResources() != null) {
            for (Resource resource : newEntity.getResources()) {
                resources.add(resourceDao.get(resource.getId()));
            }
        }
        current.setResources(resources);
    }

    @Override
    protected void copyProperties(Subject current, Subject newEntity) {
        current.setName(newEntity.getName());
        current.setImage(newEntity.getImage());

        copyResources(current, newEntity);
    }
}
