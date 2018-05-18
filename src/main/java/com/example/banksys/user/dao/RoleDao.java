package com.example.banksys.user.dao;


import com.example.banksys.user.domain.security.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleDao extends CrudRepository<Role , Long> {

    Role findByName(String name);
}
