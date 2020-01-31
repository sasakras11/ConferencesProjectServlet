package com.model.dao.impl;

import com.model.entity.Conference;
import com.model.dao.ConferenceGroup;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CrudPageableDaoConferenceImplTest {

    private CrudPageableDaoConferenceImpl conferenceDao;

    @Before
    public void init(){
        conferenceDao = new CrudPageableDaoConferenceImpl();
    }

    @Test
    public void findAllFinishedConferencesShouldReturnFinishedConferences(){
        List<Conference> actual = conferenceDao.findAll(2,3, ConferenceGroup.FINISHED);


        List<Conference> expected = new ArrayList<>();
        expected.add(Conference.builder().withId(13).withName("AMB FEST").withDate("2010-05-08").withRegisteredPeople(150).withVisitedPeople(0).build());
        expected.add(Conference.builder().withId(14).withName("BMV FEST").withDate("2011-05-08").withRegisteredPeople(150000).withVisitedPeople(0).build());
        expected.add(Conference.builder().withId(15).withName("ADIDAS FEST").withDate("2012-05-08").withRegisteredPeople(150000).withVisitedPeople(0).build());
        Assert.assertEquals(expected,actual);

    }

    @Test
    public void findAllConferencesShouldReturnAllConferences(){
        List<Conference> actual = conferenceDao.findAll(2,3, ConferenceGroup.ALL);


        List<Conference> expected = new ArrayList<>();
        expected.add(Conference.builder().withId(4).withName("DIRECTOR-WEEK").withDate("2050-05-04").withRegisteredPeople(66000).withVisitedPeople(0).build());
        expected.add(Conference.builder().withId(5).withName("IT-CONFERENCE").withDate("2040-05-05").withRegisteredPeople(100).withVisitedPeople(0).build());
        expected.add(Conference.builder().withId(6).withName("LAMA FEST").withDate("2030-05-06").withRegisteredPeople(42150000).withVisitedPeople(0).build());
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void findAllComingConferencesShouldReturnComingConferences(){
        List<Conference> actual= conferenceDao.findAll(2,3, ConferenceGroup.COMING);


        List<Conference> expected = new ArrayList<>();
        expected.add(Conference.builder().withId(5).withName("IT-CONFERENCE").withDate("2040-05-05").withRegisteredPeople(100).withVisitedPeople(0).build());
        expected.add(Conference.builder().withId(6).withName("LAMA FEST").withDate("2030-05-06").withRegisteredPeople(42150000).withVisitedPeople(0).build());
        expected.add(Conference.builder().withId(8).withName("EDA FEST").withDate("2070-05-08").withRegisteredPeople(42150000).withVisitedPeople(0).build());

        Assert.assertEquals(expected,actual);
    }


    @Test
       public void FindingByIdShouldBeCorrect(){

        Conference actual  = Conference.builder().withName("IT-WEEK").withId(1).withDate("2020-05-01").withRegisteredPeople(2000).build();
        Conference expected= conferenceDao.findById(1).get();

        Assert.assertEquals(expected,actual);

    }

       public void checkIfCountReturnsCorrectValue(){

        assertEquals(22,conferenceDao.count());
       }
}
