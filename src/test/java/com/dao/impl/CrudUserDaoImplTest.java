package com.dao.impl;

import com.context.AppContext;
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
import java.util.ArrayList;
import java.util.List;


public class CrudUserDaoImplTest {



    CrudUserDaoImpl userDao;

    @Before
    public void init() {

        userDao = new CrudUserDaoImpl();
        try {
            Connection connection =  DataSource.getConnection();
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
                .withPassword(AppContext.getPasswordUtil().getHashedPassword("pass"))
                .withUsername("alex")
                .withStatus(Role.valueOf("ADMIN")).build();


        Assert.assertEquals(user,userDao.findById(1).get());
    }

     @Test
     public void findByUserName(){
         User user = new User.UserBuilder()
                 .withId(1)
                 .withPassword(AppContext.getPasswordUtil().getHashedPassword("pass"))
                 .withUsername("alex")
                 .withStatus(Role.valueOf("ADMIN")).build();



         Assert.assertEquals(user,userDao.findByUsername("alex").get());
     }

     @Test
      public void findByRole(){
         List<User> expected = new ArrayList<>();
        User ivan = new User.UserBuilder().withUsername("ivan").withPassword("288f1cece25978fc5a60ed916067a97e7ba555019407cb0777f85ed637155a1ac00f961f2b0a21261c51c097d6df22ca0affc6d880a201adb1f950fd1756af13")
                .withStatus(Role.SPEAKER).withId(2).build();
        User olga = new User.UserBuilder().withUsername("olga").withPassword("51788beddf9484765b50585dfb6dc55032f7b8b77cd7c844caacd326a347f4c9cb1cb2b1d4a979d4c301a5859706428c0693093ea2f1f6a2ff9021b7779bb94c")
                .withStatus(Role.SPEAKER).withId(4).build();
        expected.add(ivan);
        expected.add(olga);

        List<User> actual = userDao.findByRole(Role.SPEAKER);

        Assert.assertEquals(expected,actual);
     }
}
