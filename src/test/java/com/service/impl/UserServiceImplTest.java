package com.service.impl;


import com.context.AppContext;
import com.dao.ConferenceGroup;
import com.dao.DataSource;
import com.dao.impl.CrudPageableDaoConferenceImpl;
import com.dao.impl.CrudUserDaoImpl;
import com.entity.Conference;
import com.entity.User;
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

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
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


    @Mock
    private CrudUserDaoImpl userDao;
    @Mock
    private PasswordUtil passwordUtil;

    @Mock
    private Validator validator;

    @InjectMocks
    private UserServiceImpl service;

    @After
    public void resetMocks() {
        reset(userDao, passwordUtil);
    }

    @Before
    public void init() {
        DataSource source = new DataSource("src/main/resources/db.properties");
        service = AppContext.getUserService();

        DataSource dataSource = new DataSource("src/test/resources/h2.properties");
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            String dbSchemaQuery = new String(Files.readAllBytes(Paths.get("src/test/resources/dbSchema.sql")));
            System.out.println(dbSchemaQuery);
            statement.executeUpdate(dbSchemaQuery);

            String dbAddQuery = new String(Files.readAllBytes(Paths.get("src/test/resources/addDBValues.sql")));
            System.out.println(dbAddQuery);
            statement.executeUpdate(dbAddQuery);
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void whenCredentialsAreWrongLoginShouldReturnFalse() {
        when(passwordUtil.getHashedPassword(INCORRECT_PASSWORD)).thenReturn(ENCODED_INCORRECT_PASSWORD);
        when(userDao.findByUsername(anyString())).thenReturn(Optional.empty());
        final boolean isLogin = service.login(USERNAME, INCORRECT_PASSWORD);

        Assert.assertFalse(isLogin);

        verify(passwordUtil).getHashedPassword(eq(ENCODED_INCORRECT_PASSWORD));
        verify(userDao).findByUsername(eq(ENCODED_INCORRECT_PASSWORD));
        verifyZeroInteractions(passwordUtil);

    }

    @Test
    public void whenCredentialsAreVerifiedLoginShouldReturnTrue() {
        when(passwordUtil.getHashedPassword(anyString())).thenReturn(ENCODED_PASSWORD);
        when(userDao.findByUsername(anyString())).thenReturn(Optional.of(USER));

        final boolean isLogin = service.login(USERNAME, PASSWORD);

        Assert.assertTrue(isLogin);


        verify(passwordUtil).getHashedPassword(eq(PASSWORD));
        verify(userDao).findByUsername(eq(USERNAME));
        verifyZeroInteractions(passwordUtil);

    }

    @Test(expected = RuntimeException.class)
    public void whenUserFailedValidationHeShouldNoRegistered() {
        when(userDao.findByUsername(any())).thenReturn(Optional.of(USER));

        service.register(USER.getUsername(),USER.getPassword());

    }

    @Test
    public void findAllConferencesShouldReturnFirstPage() {
        List<Conference> expected = new ArrayList<>();
        expected.add(Conference.builder().withId(1).withName("IT-WEEK").withDate("2020-05-01").build());
        expected.add(Conference.builder().withId(2).withName("BEER-FEST").withDate("2020-05-02").build());
        expected.add(Conference.builder().withId(3).withName("TWENY FEST").withDate("2010-05-03").build());
        expected.add(Conference.builder().withId(4).withName("DIRECTOR-WEEK").withDate("2050-05-04").build());
        expected.add(Conference.builder().withId(5).withName("IT-CONFERENCE").withDate("2040-05-05").build());

        List<Conference> actual = service.findAllConferences(-1, ConferenceGroup.ALL);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnLastPageIfPageNumberIsBiggerThenMax() {

        List<Conference> expected = new ArrayList<>();
        expected.add(Conference.builder().withId(21).withName("WAR FEST").withDate("2018-05-08").build());
        expected.add(Conference.builder().withId(22).withName("ASR FEST").withDate("2019-05-08").build());

        List<Conference> actual = service.findAllConferences(10000, ConferenceGroup.ALL);

        assertEquals(expected, actual);
    }


}
