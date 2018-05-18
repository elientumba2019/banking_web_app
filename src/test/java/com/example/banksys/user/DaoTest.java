package com.example.banksys.user;

import com.example.banksys.user.dao.RoleDao;
import com.example.banksys.user.domain.security.Role;
import com.example.banksys.user.service.RoleService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DaoTest {



    @Autowired
    RoleDao dao;

    @Autowired
    RoleService roleService;

    Logger loggerl = LoggerFactory.getLogger(this.getClass().getName());





    @Before
    public void prepare(){

    }



    @Test
    public void testRole(){
        Role role = dao.findByName("ROLE_USER");
        Assert.assertNotNull(role);

    }


    @Test
    public void roleService(){
        Role role = roleService.findByName("ROLE_USER");
        Assert.assertNotNull(role);
        System.out.println("Test role Dao " +  role.getName());
    }
}
