package com.example.banksys.user.service.impl;
import com.example.banksys.user.dao.RoleDao;
import com.example.banksys.user.dao.UserDao;
import com.example.banksys.user.domain.User;
import com.example.banksys.user.domain.security.UserRole;
import com.example.banksys.user.service.AccountService;
import com.example.banksys.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;


/**
 * user service class
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {


    private UserDao userDao;
    private RoleDao roleDao;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private BCryptPasswordEncoder passwordEncoder;
    private AccountService accountService;



    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao,  BCryptPasswordEncoder passwordEncoder , AccountService accountService) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
        this.accountService = accountService;
    }



    @Override
    public void saveUser(User user){
        userDao.save(user);
    }



    @Override
    public User findByUsername(String username){
        return userDao.findByUsername(username);
    }




    @Override
    public User findByEmail(String email){
        return userDao.findByEmail(email);
    }




    @Override
    public boolean checkUserExist(String username , String email){
        if(checkUsernameExist(username) || checkEmailExist(email)){
            return true;
        }
        else {
            return false;
        }
    }





    @Override
    public boolean checkUsernameExist(String username){
        return (null != findByUsername(username));

    }




    @Override
    public boolean checkEmailExist(String email){
        return (null != findByEmail(email));
    }






    @Override
    public User createUser(User user , Set<UserRole> userRoles) {
        User localUser = userDao.findByUsername(user.getUsername());

        if(localUser != null){
            logger.info("User with username{} alreaday exist" , user.getUsername());
            throw new RuntimeException("User with id : " + localUser.getUserId() + " Already exist");
        }
        else{
            String encryptedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);

            for(UserRole ur : userRoles){
                roleDao.save(ur.getRole());
            }

            user.getUserRoles().addAll(userRoles);
            user.setPrimaryAccount(accountService.createPrimaryAccount());
            user.setSavingAccount(accountService.createSavingAccount());


            localUser = userDao.save(user);

        }

        return localUser;
    }

}









