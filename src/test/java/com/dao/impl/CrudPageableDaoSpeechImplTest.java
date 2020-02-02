package com.dao.impl;


import com.entity.Speech;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CrudPageableDaoSpeechImplTest {

    CrudPageableDaoSpeechImpl speechDao;


    @Before
    public void init() {
        speechDao = new CrudPageableDaoSpeechImpl();
    }

    @Test
    public void getListOfSpeechesByConferenceIdShouldBeCorrect() {
        List<Speech> list = speechDao.getSpeechesByConferenceId(3);
        List<Speech> expected = new ArrayList<>();
        expected.add(Speech.builder().withTopic("vstre4a s ivanom groznum").withId(7).withSuggestedTopic("").withSpeakerId(4).withEndHour(5).withStartHour(7).build());
        expected.add(Speech.builder().withTopic("top filmov").withId(8).withSpeakerId(2).withSuggestedTopic("").withEndHour(5).withStartHour(8).build());
        expected.add(Speech.builder().withTopic("100 ottenkov belogo").withId(9).withSuggestedTopic("").withSpeakerId(4).withEndHour(5).withStartHour(9).build());
        expected.add(Speech.builder().withTopic("vlada Rosiii").withId(10).withSpeakerId(2).withSuggestedTopic("").withEndHour(5).withStartHour(10).build());
        expected.add(Speech.builder().withTopic("O garri pottere").withId(11).withSpeakerId(4).withSuggestedTopic("").withEndHour(5).withStartHour(11).build());

        Assert.assertEquals(list, expected);
    }

    @Test
    public void findById() {
        Speech expected = Speech.builder().withId(11).withStartHour(11).withSuggestedTopic("").withEndHour(5).withSpeakerId(4).withTopic("O garri pottere").build();
        Assert.assertEquals(expected, speechDao.findById(11).get());
    }

    @Test
    public void getSpeechesByUserId() {
        List<Speech> actual = speechDao.getSpeechesByUserId(5);

        ArrayList<Speech> expected = new ArrayList<>();
        expected.add(Speech.builder().withId(9).withSpeakerId(4).withEndHour(5).withSuggestedTopic("").withStartHour(9).withTopic("100 ottenkov belogo").build());
        expected.add(Speech.builder().withId(10).withSpeakerId(2).withEndHour(5).withSuggestedTopic("").withStartHour(10).withTopic("vlada Rosiii").build());
        expected.add(Speech.builder().withId(19).withSpeakerId(2).withEndHour(24).withSuggestedTopic("").withStartHour(23).withTopic("Vstre4a s Olegom Velikim").build());
        Assert.assertEquals(expected, actual);


    }

    @Test
    public void getSpeechesByConferenceId() {
        List<Speech> actual = speechDao.getSpeechesByConferenceId(1);
        ArrayList<Speech> expected = new ArrayList<>();
        expected.add(Speech.builder().withTopic("vlada Ukrainy").withStartHour(2).withSuggestedTopic("").withEndHour(5).withSpeakerId(2).build());
        expected.add(Speech.builder().withTopic("Pro nas").withStartHour(1).withSuggestedTopic("").withEndHour(2).withSpeakerId(4).build());
        expected.add(Speech.builder().withTopic("O stb").withStartHour(2).withSuggestedTopic("").withEndHour(5).withSpeakerId(2).build());
        Assert.assertEquals(expected, actual);
    }
}
