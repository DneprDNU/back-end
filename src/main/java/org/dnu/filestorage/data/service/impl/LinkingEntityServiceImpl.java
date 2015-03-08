package org.dnu.filestorage.data.service.impl;

import org.dnu.filestorage.data.dao.LinkingEntityDAO;
import org.dnu.filestorage.data.dao.SpecialityDAO;
import org.dnu.filestorage.data.dao.SubjectDAO;
import org.dnu.filestorage.data.dao.TeacherDAO;
import org.dnu.filestorage.data.model.LinkingEntity;
import org.dnu.filestorage.data.service.LinkingEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author demyura
 * @since 14.11.14
 */
@Service
@Transactional
public class LinkingEntityServiceImpl implements LinkingEntityService {

    @Autowired
    private LinkingEntityDAO dao;
    @Autowired
    private SpecialityDAO specialityDao;
    @Autowired
    private SubjectDAO subjectDAO;
    @Autowired
    private TeacherDAO teacherDAO;

    @Override
    public List<LinkingEntity> list() {
        return dao.list();
    }

    @Override
    public LinkingEntity get(Long id) {
        return dao.get(id);
    }

    @Override
    public LinkingEntity update(LinkingEntity entity) {
        LinkingEntity current = dao.get(entity.getId());
        current.setSpeciality(entity.getSpeciality() == null ? null : specialityDao.get(entity.getSpeciality().getId()));
        current.setTeacher(entity.getTeacher() == null ? null : teacherDAO.get(entity.getTeacher().getId()));
        current.setSubject(entity.getSubject() == null ? null : subjectDAO.get(entity.getSubject().getId()));
        return dao.update(entity);
    }

    @Override
    public LinkingEntity create(LinkingEntity entity) {
        LinkingEntity current = dao.create(new LinkingEntity());
        current.setSpeciality(entity.getSpeciality() == null ? null : specialityDao.get(entity.getSpeciality().getId()));
        current.setTeacher(entity.getTeacher() == null ? null : teacherDAO.get(entity.getTeacher().getId()));
        current.setSubject(entity.getSubject() == null ? null : subjectDAO.get(entity.getSubject().getId()));
        return dao.update(current);
    }

    @Override
    public void remove(Long id) {
        dao.remove(get(id));
    }

    @Override
    public long getCount() {
        return dao.getCount();
    }

    @Override
    public List<LinkingEntity> list(int from, int to) {
        return dao.list(from, to);
    }

    @Override
    public void remove(LinkingEntity entity) {
        dao.remove(entity);
    }


}
