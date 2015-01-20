package org.dnu.filestorage.data;

import org.dnu.filestorage.data.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;


@Service("userDetailsService")
public class HibernateUserDetailsService implements UserDetailsService {

    //get user from the database, via Hibernate
    @Autowired
    private UserDAO userDao;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {

        org.dnu.filestorage.data.model.User user = userDao.findByUserName(username);

        return new User(user.getUsername(), user.getPassword(),
                user.isEnabled(), true, true, true, Arrays.asList(new SimpleGrantedAuthority(user.getUserRole())));

    }


}

