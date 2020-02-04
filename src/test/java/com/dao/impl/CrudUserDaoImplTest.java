package com.dao.impl;

import com.dao.DataSource;
import com.entity.Role;
import com.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;


public class CrudUserDaoImplTest {



    CrudUserDaoImpl userDao;

    @Before
    public void init() {

        userDao = new CrudUserDaoImpl(new DataSource("src/test/resources/h2.properties"));
        DataSource dataSource = new DataSource("src/test/resources/h2.properties");
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            String dbSchemaQuery = new String(Files.readAllBytes(Paths.get("src/test/resources/dbSchema.sql")));
            System.out.println(dbSchemaQuery);
            statement.executeUpdate(dbSchemaQuery);

            String dbAddQuery  = new String(Files.readAllBytes(Paths.get("src/test/resources/addDBValues.sql")));
            System.out.println(dbAddQuery);
            statement.executeUpdate(dbAddQuery);
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

     @Test
     public void findByUserName(){
         User user = new User.UserBuilder()
                 .withId(1)
                 .withPassword("pass")
                 .withUsername("alex")
                 .withStatus(Role.valueOf("ADMIN")).build();


         Assert.assertEquals(user,userDao.findByUsername("alex").get());
     }
}
