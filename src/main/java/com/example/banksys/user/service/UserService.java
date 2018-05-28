package com.example.banksys.user.service;
import com.example.banksys.user.domain.User;
import com.example.banksys.user.domain.security.UserRole;

import java.util.List;
import java.util.Set;

public interface UserService {

    void saveUser(User user);
    User findByUsername(String username);
    User findByEmail(String email);
    boolean checkUserExist(String username , String email);
    boolean checkUsernameExist(String username);
    boolean checkEmailExist(String email);
    User createUser(User user , Set<UserRole> userRoles);
    List<User> findUserList();
    void enableUser(String username);
    void disable(String username);
}
