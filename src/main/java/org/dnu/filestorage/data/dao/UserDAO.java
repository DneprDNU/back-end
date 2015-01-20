package org.dnu.filestorage.data.dao;


import org.dnu.filestorage.data.model.User;

public interface UserDAO extends GenericDAO<User> {
    public User findByUserName(String username);
}
