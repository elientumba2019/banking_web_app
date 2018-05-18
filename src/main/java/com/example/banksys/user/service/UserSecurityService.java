package com.example.banksys.user.service;

import com.example.banksys.user.dao.UserDao;
import com.example.banksys.user.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * user security service class
 */
@Service
public class UserSecurityService implements UserDetailsService {

    //app logger
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    private UserDao userDao;



    @Autowired
    public UserSecurityService(UserDao userDao) {
        this.userDao = userDao;
    }



    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.findByUsername(s);
        if(user == null){
            LOG.warn("Username{} not found", s);
            throw new UsernameNotFoundException("Username : " + s + " not found");
        }
        return  user;
    }
}
