package org.dnu.filestorage.service.dao;


import org.dnu.filestorage.model.User;

public interface UserDAO extends GenericDAO<User> {
    public User findByUserName(String username);
}
