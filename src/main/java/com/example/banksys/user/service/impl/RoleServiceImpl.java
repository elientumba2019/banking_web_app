package com.example.banksys.user.service.impl;
import com.example.banksys.user.dao.RoleDao;
import com.example.banksys.user.domain.security.Role;
import com.example.banksys.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;


    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }


    @Override
    public Role findByName(String name) {
        return roleDao.findByName(name);
    }
}
