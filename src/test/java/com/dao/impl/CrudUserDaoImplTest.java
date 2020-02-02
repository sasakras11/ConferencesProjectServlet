package com.dao.impl;

import com.entity.Role;
import com.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class CrudUserDaoImplTest {



    CrudUserDaoImpl userDao;
    @Before
    public void init(){
        userDao = new CrudUserDaoImpl();
    }


    @Test
    public void findById() {

        User user = new User.UserBuilder()
                .withId(1)
                .withPassword("pass")
                .withUsername("alex")
                .withStatus(Role.valueOf("ADMIN")).build();


        Assert.assertEquals(user,userDao.findById(1).get());
    }
}
