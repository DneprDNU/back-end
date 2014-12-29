package org.dnu.filestorage.service.dao.impl;

import org.dnu.filestorage.model.User;
import org.dnu.filestorage.service.dao.UserDAO;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {

    @PersistenceContext
    protected EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public User findByUserName(String username) {

        List<User> users = new ArrayList<User>();


        users = entityManager.createQuery("select u from User u where u.username=:name")
                .setParameter("name", username).getResultList();

        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }

    @Override
    public User create(User entity) {
        Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
        entity.setPassword(passwordEncoder.encodePassword(entity.getPassword(), null));
        entityManager.persist(entity);
        return entity;
    }
}
