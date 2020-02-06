package com.dao.impl;


import com.dao.DataSource;
import com.entity.Speech;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CrudPageableDaoSpeechImplTest {

    CrudPageableDaoSpeechImpl speechDao;

        @Before
        public void init() {

            speechDao= new CrudPageableDaoSpeechImpl(new DataSource("src/test/resources/h2.properties"));
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
    public void getListOfSpeechesByConferenceIdShouldBeCorrect() {
        List<Speech> list = speechDao.getSpeechesByConferenceId(3);
        List<Speech> expected = new ArrayList<>();
        expected.add(Speech.builder().withTopic("vstre4a s ivanom groznum").withId(7).withSuggestedTopic("").withEndHour(5).withStartHour(7).withVisitedPeople(100).withRegisteredPeople(200).build());
        expected.add(Speech.builder().withTopic("top filmov").withId(8).withSuggestedTopic("").withEndHour(5).withStartHour(8).withVisitedPeople(20).withRegisteredPeople(50).build());
        expected.add(Speech.builder().withTopic("100 ottenkov belogo").withId(9).withSuggestedTopic("").withEndHour(5).withStartHour(9).withVisitedPeople(20).withRegisteredPeople(15).build());
        expected.add(Speech.builder().withTopic("vlada Rosiii").withId(10).withSuggestedTopic("").withEndHour(5).withStartHour(10).withVisitedPeople(12).withRegisteredPeople(32).build());
        expected.add(Speech.builder().withTopic("O garri pottere").withId(11).withSuggestedTopic("").withEndHour(5).withStartHour(11).withVisitedPeople(760).withRegisteredPeople(31).build());

        Assert.assertEquals(list, expected);
    }

    @Test
    public void findById() {
        Speech expected = Speech.builder().withId(11).withStartHour(11).withSuggestedTopic("").withEndHour(5).withTopic("O garri pottere").withRegisteredPeople(31).withVisitedPeople(760).build();
        Assert.assertEquals(expected, speechDao.findById(11).get());

    }

    @Test
    public void getSpeechesByUserId() {
        List<Speech> actual = speechDao.getSpeechesByUserId(5);

        ArrayList<Speech> expected = new ArrayList<>();
        expected.add(Speech.builder().withId(9).withRegisteredPeople(15).withVisitedPeople(20).withEndHour(5).withSuggestedTopic("").withStartHour(9).withTopic("100 ottenkov belogo").build());
        expected.add(Speech.builder().withId(10).withRegisteredPeople(32).withVisitedPeople(12).withEndHour(5).withSuggestedTopic("").withStartHour(10).withTopic("vlada Rosiii").build());
        expected.add(Speech.builder().withId(19).withRegisteredPeople(500).withVisitedPeople(260).withEndHour(24).withSuggestedTopic("").withStartHour(23).withTopic("Vstre4a s Olegom Velikim").build());
        Assert.assertEquals(expected, actual);


    }

    @Test
    public void getSpeechesByConferenceId() {
        List<Speech> actual = speechDao.getSpeechesByConferenceId(1);
        ArrayList<Speech> expected = new ArrayList<>();
        expected.add(Speech.builder().withId(1).withVisitedPeople(100).withRegisteredPeople(200).withTopic("vlada Ukrainy").withStartHour(2).withSuggestedTopic("").withEndHour(5).build());
        expected.add(Speech.builder().withTopic("Pro nas").withId(2).withRegisteredPeople(50).withVisitedPeople(300).withStartHour(1).withSuggestedTopic("").withEndHour(2).build());
        expected.add(Speech.builder().withTopic("O stb").withId(3).withVisitedPeople(0).withRegisteredPeople(50).withStartHour(2).withSuggestedTopic("").withEndHour(5).build());
        Assert.assertEquals(expected.get(0), actual.get(0));
    }


    @Test
    public void getSpeechesByUserIdAndConferenceId(){
            List<Speech> actual=  speechDao.getSpeechesByUserIdAndConferenceId(1,1);
            List<Speech> expected = new ArrayList<>();
            expected.add(Speech.builder().withId(1).withTopic("vlada Ukrainy").withSuggestedTopic("").withStartHour(2).withRegisteredPeople(200).withVisitedPeople(100).withEndHour(5).build());
            expected.add(Speech.builder().withId(2).withTopic("Pro nas").withStartHour(1).withSuggestedTopic("").withRegisteredPeople(50).withVisitedPeople(300).withEndHour(2).build());

                Assert.assertEquals(expected,actual);
    }
}
