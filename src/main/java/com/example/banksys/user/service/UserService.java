package com.example.banksys.user.service;
import com.example.banksys.user.domain.User;
import org.springframework.stereotype.Service;

public interface UserService {

    void saveUser(User user);
    User findByUsername(String username);
    User findByEmail(String email);
    boolean checkUserExist(String username , String email);
    boolean checkUsernameExist(String username);
    boolean checkEmailExist(String email);
}
