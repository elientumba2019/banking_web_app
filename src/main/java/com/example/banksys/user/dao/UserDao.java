package com.example.banksys.user.dao;
import com.example.banksys.user.domain.User;
import org.springframework.data.repository.CrudRepository;



public interface UserDao extends CrudRepository<User, Long> {

    User findByUsername(String username);
    User findByEmail(String email);
}
