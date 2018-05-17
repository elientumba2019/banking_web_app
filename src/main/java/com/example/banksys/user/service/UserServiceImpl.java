package com.example.banksys.user.service;
import com.example.banksys.user.dao.UserDao;
import com.example.banksys.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * user service class
 */
@Service
public class UserServiceImpl implements UserService{


    private UserDao userDao;


    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
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

}









