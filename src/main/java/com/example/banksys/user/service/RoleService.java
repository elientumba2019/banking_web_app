package com.example.banksys.user.service;

import com.example.banksys.user.domain.security.Role;

public interface RoleService {
    Role findByName(String name);
}
