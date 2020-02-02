package com.dao.impl;

import com.entity.Rating;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class RatingDaoTest {


    private RatingDao ratingDao;
    private Rating rating;

  @Before
    public void init(){
        ratingDao = new RatingDao();
        rating = new Rating(2,2,10);
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
