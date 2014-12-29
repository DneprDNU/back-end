package org.dnu.filestorage.service;

import java.util.*;


import org.dnu.filestorage.service.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("userDetailsService")
public class HibernateUserDetailsService implements UserDetailsService {

    //get user from the database, via Hibernate
    @Autowired
    private UserDAO userDao;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {

        org.dnu.filestorage.model.User user = userDao.findByUserName(username);

        return new User(user.getUsername(), user.getPassword(),
                user.isEnabled(), true, true, true, Arrays.asList(new SimpleGrantedAuthority(user.getUserRole())));

    }


}

