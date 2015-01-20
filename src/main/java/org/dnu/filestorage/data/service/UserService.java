package org.dnu.filestorage.data.service;


import org.dnu.filestorage.data.model.User;

public interface UserService extends GenericService<User> {
    public User findByUserName(String username);
}
