package com.dao.impl;

import com.dao.DataSource;
import com.entity.Rating;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;


public class RatingDaoTest {


    private RatingDao ratingDao;
    private Rating rating;
    @Before
    public void init() {
        rating = new Rating(2,2,10);

        ratingDao= new RatingDao();
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
    public void findBySpeakerId() {
        Rating expected = new Rating(1,2,15);
        Assert.assertEquals(expected,ratingDao.findBySpeakerId(2).get());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void findById() {
       ratingDao.findById(1);
    }
    @Test
    public void bonusesShouldBeCorrect(){
      Assert.assertEquals(95,rating.getBonuses());

    }
}
