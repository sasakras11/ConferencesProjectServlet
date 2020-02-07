package com.service.impl;


import com.dao.ConferenceGroup;
import com.dao.CrudPageableConferenceDao;
import com.dao.impl.CrudUserDaoImpl;
import com.entity.Conference;
import com.entity.User;
import com.exception.ValidationException;
import com.service.util.PasswordUtil;
import com.service.util.Validator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    private static final String ENCODED_PASSWORD = "encoded_password";
    private static final String PASSWORD = "password";
    private static final String USERNAME = "username";
    private static final String INCORRECT_PASSWORD = "INCORRECT_PASSWORD";
    private static final String ENCODED_INCORRECT_PASSWORD = "encode_incorrect_password";
    private static final User USER =
            User.builder()
                    .withUsername(USERNAME)
                    .withPassword(ENCODED_PASSWORD)
                    .build();


    private static final List<Conference> ALL_CONFERENCES = new ArrayList<>();

    private static final List<Conference> FIRST_PAGE_OF_CONFERENCES = new ArrayList<>();
    private static final List<Conference> LAST_PAGE_OF_CONFERENCES = new ArrayList<>();
    private static final List<Conference> SECOND_PAGE_OF_CONFERENCE = new ArrayList<>();
    private static final ConferenceGroup ALL = ConferenceGroup.ALL;


    @Mock
    private CrudPageableConferenceDao conferenceDao;
    @Mock
    private CrudUserDaoImpl userDao;
    @Mock
    private PasswordUtil passwordUtil;

    @Mock
    private Validator validator;

    @InjectMocks
    private UserServiceImpl service;

    @Before
    public void init() {
        ALL_CONFERENCES.add(Conference.builder().withId(1).withName("IT-WEEK").withDate("2026-05-01").build());
        ALL_CONFERENCES.add(Conference.builder().withId(2).withName("RR-FEST").withDate("2820-11-02").build());
        ALL_CONFERENCES.add(Conference.builder().withId(3).withName("TWENY FEST").withDate("2710-12-03").build());
        ALL_CONFERENCES.add(Conference.builder().withId(4).withName("DIRECTOR-WEEK").withDate("2550-11-04").build());
        ALL_CONFERENCES.add(Conference.builder().withId(5).withName("IT-CONFERENCE").withDate("2340-05-05").build());
        ALL_CONFERENCES.add(Conference.builder().withId(6).withName("ONE FEST").withDate("2068-05-08").build());
        ALL_CONFERENCES.add(Conference.builder().withId(7).withName("TWO FEST").withDate("2519-05-08").build());
        ALL_CONFERENCES.add(Conference.builder().withId(8).withName("FR FEST").withDate("2419-05-08").build());
        ALL_CONFERENCES.add(Conference.builder().withId(9).withName("GG FEST").withDate("2319-05-11").build());
        ALL_CONFERENCES.add(Conference.builder().withId(10).withName("RAP FEST").withDate("2029-05-08").build());
        ALL_CONFERENCES.add(Conference.builder().withId(11).withName("KP TEST").withDate("2019-05-07").build());
        ALL_CONFERENCES.add(Conference.builder().withId(12).withName("RG FEST").withDate("2019-05-06").build());
        ALL_CONFERENCES.add(Conference.builder().withId(13).withName("GK TEST").withDate("2019-05-18").build());

        LAST_PAGE_OF_CONFERENCES.add(ALL_CONFERENCES.get(10));
        LAST_PAGE_OF_CONFERENCES.add(ALL_CONFERENCES.get(11));
        LAST_PAGE_OF_CONFERENCES.add(ALL_CONFERENCES.get(12));

        FIRST_PAGE_OF_CONFERENCES.add(ALL_CONFERENCES.get(0));
        FIRST_PAGE_OF_CONFERENCES.add(ALL_CONFERENCES.get(1));
        FIRST_PAGE_OF_CONFERENCES.add(ALL_CONFERENCES.get(2));
        FIRST_PAGE_OF_CONFERENCES.add(ALL_CONFERENCES.get(3));
        FIRST_PAGE_OF_CONFERENCES.add(ALL_CONFERENCES.get(4));

        SECOND_PAGE_OF_CONFERENCE.add(ALL_CONFERENCES.get(5));
        SECOND_PAGE_OF_CONFERENCE.add(ALL_CONFERENCES.get(6));
        SECOND_PAGE_OF_CONFERENCE.add(ALL_CONFERENCES.get(7));
        SECOND_PAGE_OF_CONFERENCE.add(ALL_CONFERENCES.get(8));
        SECOND_PAGE_OF_CONFERENCE.add(ALL_CONFERENCES.get(9));

    }

    @After
    public void resetMocks() {
        reset(userDao, passwordUtil,conferenceDao);
    }


    @Test
    public void whenThereISNotSuchUserLoginShouldReturnOptionalEmpty() {
        when(passwordUtil.getHashedPassword(INCORRECT_PASSWORD)).thenReturn(ENCODED_INCORRECT_PASSWORD);
        when(userDao.findByUsername(anyString())).thenReturn(Optional.empty());
        Optional<User> user = service.login(USERNAME, INCORRECT_PASSWORD);


        Assert.assertFalse(user.isPresent());
        verify(passwordUtil).getHashedPassword(eq(INCORRECT_PASSWORD));
        verify(userDao).findByUsername(eq(USERNAME));
        verifyZeroInteractions(passwordUtil);

    }

    @Test
    public void whenCredentialsAreVerifiedLoginShouldReturnTrue() {
        when(passwordUtil.getHashedPassword(anyString())).thenReturn(ENCODED_PASSWORD);
        when(userDao.findByUsername(anyString())).thenReturn(Optional.of(USER));

        Optional<User> user = service.login(USERNAME, PASSWORD);

        Assert.assertTrue(user.isPresent());


        verify(passwordUtil).getHashedPassword(eq(PASSWORD));
        verify(userDao).findByUsername(eq(USERNAME));
        verifyZeroInteractions(passwordUtil);

    }

    @Test(expected = ValidationException.class)
    public void whenUserIsAlreadyInDatabaseHeShouldNoRegister() {
        doNothing().when(validator).validate(any(),any());
        when(userDao.findByUsername(any())).thenReturn(Optional.of(USER));

         service.register(USER.getUsername(), USER.getPassword());

        verify(validator).validate("1_24&","1(*%");
        verify(userDao).findByUsername(eq(USERNAME));

    }

    @Test
    public void findAllConferencesShouldReturnFirstPageIfSelectedPageIsTooBig() {
        when(conferenceDao.count(any())).thenReturn(13);
        when(conferenceDao.findAll(100,5)).thenReturn(ALL_CONFERENCES);

        Assert.assertEquals( LAST_PAGE_OF_CONFERENCES,service.findAllConferences(100, ALL));


    }

    @Test
    public void findAllConferencesShouldReturnFirstPageIfSelectedPageIsNegative() {

        Assert.assertEquals(FIRST_PAGE_OF_CONFERENCES,service.findAllConferences(-1, ALL));
        when(conferenceDao.count(any())).thenReturn(13);


        verify(conferenceDao.findAll(-1,5));

    }

    @Test
    public void findAllConferencesShouldReturnValidPageIfPageIsIsAvailable() {

        when(conferenceDao.findAll(anyInt(),anyInt())).thenReturn(ALL_CONFERENCES);
        when(conferenceDao.count(any())).thenReturn(13);
        Assert.assertEquals(SECOND_PAGE_OF_CONFERENCE,service.findAllConferences(2, ALL));


        verify(conferenceDao.findAll(2,5));
    }



}
