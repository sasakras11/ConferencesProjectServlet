package com.dao.impl;

import com.dao.ConferenceGroup;
import com.dao.DataSource;
import com.entity.Conference;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.xml.crypto.Data;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CrudPageableDaoConferenceImplTest {

    private CrudPageableDaoConferenceImpl conferenceDao;

    @Before
    public void init() {

        conferenceDao = new CrudPageableDaoConferenceImpl();

        try {
            DataSource.setNewProperties("/home/alex/IdeaProjects/Final_Servlet_Project/src/test/resources/h2.properties");
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
    public void findAllFinishedConferencesShouldReturnFinishedConferences() {
        List<Conference> actual = conferenceDao.findAll(2, 3, ConferenceGroup.FINISHED);


        List<Conference> expected = new ArrayList<>();
        expected.add(Conference.builder().withId(13).withName("AMB FEST").withDate("2010-05-08").build());
        expected.add(Conference.builder().withId(14).withName("BMV FEST").withDate("2011-05-08").build());
        expected.add(Conference.builder().withId(15).withName("ADIDAS FEST").withDate("2012-05-08").build());
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void findAllConferencesShouldReturnAllConferences() {
        List<Conference> actual = conferenceDao.findAll(2, 3, ConferenceGroup.ALL);


        List<Conference> expected = new ArrayList<>();
        expected.add(Conference.builder().withId(4).withName("DIRECTOR-WEEK").withDate("2050-05-04").build());
        expected.add(Conference.builder().withId(5).withName("IT-CONFERENCE").withDate("2040-05-05").build());
        expected.add(Conference.builder().withId(6).withName("LAMA FEST").withDate("2030-05-06").build());
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void findAllComingConferencesShouldReturnComingConferences() {
        List<Conference> actual = conferenceDao.findAll(2, 3, ConferenceGroup.COMING);


        List<Conference> expected = new ArrayList<>();
        expected.add(Conference.builder().withId(5).withName("IT-CONFERENCE").withDate("2040-05-05").build());
        expected.add(Conference.builder().withId(6).withName("LAMA FEST").withDate("2030-05-06").build());
        expected.add(Conference.builder().withId(8).withName("EDA FEST").withDate("2070-05-08").build());

        Assert.assertEquals(expected, actual);
    }


    @Test
    public void FindingByIdShouldBeCorrect() {

        Conference actual = Conference.builder().withName("IT-WEEK").withId(1).withDate("2020-05-01").build();
        Conference expected = conferenceDao.findById(1).get();

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void checkIfCountReturnsCorrectValueOfAllConferences() {

        assertEquals(22, conferenceDao.count(ConferenceGroup.ALL));
    }

    @Test
    public void checkIfCountReturnsCorrectValueOfFinishedConferences() {
        assertEquals(13, conferenceDao.count(ConferenceGroup.FINISHED));
    }

    @Test
    public void checkIfCountReturnsCorrectValueOfComingConferences() {
        assertEquals(9, conferenceDao.count(ConferenceGroup.COMING));
    }
}
