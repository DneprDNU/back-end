package org.dnu.filestorage.data.service.impl;

import org.dnu.filestorage.data.dao.UserDAO;
import org.dnu.filestorage.data.model.User;
import org.dnu.filestorage.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl extends GenericServiceImpl<UserDAO, User> implements UserService {

    @Autowired
    public UserServiceImpl(UserDAO dao) {
        super(dao);
    }

    @SuppressWarnings("unchecked")
    public User findByUserName(String username) {
        return dao.findByUserName(username);
    }

    @Override
    public User create(User entity) {
        return dao.create(entity);
    }
}
