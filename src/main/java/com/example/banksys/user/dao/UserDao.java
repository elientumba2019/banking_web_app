package com.example.banksys.user.dao;
import com.example.banksys.user.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface UserDao extends CrudRepository<User, Long> {

    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findAll();
}
