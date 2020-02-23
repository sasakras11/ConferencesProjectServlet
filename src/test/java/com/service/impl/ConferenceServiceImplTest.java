package com.service.impl;


import com.dao.CrudPageableConferenceDao;
import com.entity.Conference;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ConferenceServiceImplTest {

    @Mock
    private CrudPageableConferenceDao conferenceDao;

    @InjectMocks
    private ConferenceServiceImpl conferenceService;

    @After
    public void resetMocks() {
        reset(conferenceDao);
    }

    @Test
    public void ifSpeechIdIsNotValidShouldReturnOptionalEmpty(){
        Optional<Conference> conference = conferenceService.findConferenceBySpeechId("not int");
        Assert.assertEquals(Optional.empty(),conference);
    }
}
