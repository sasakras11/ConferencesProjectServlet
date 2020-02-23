package com.service.impl;

import com.dao.CrudPageableConferenceDao;
import com.dao.CrudPageableSpeechDao;
import com.dao.UserDao;
import com.entity.Conference;
import com.entity.Speech;
import com.entity.User;
import com.exception.URLInjectionException;
import com.service.SpeechService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SpeechServiceImplTest {


    private static final int ID = 1;
    private static final String TOPIC = "topic";
    private static final String SUGGESTED_TOPIC = "suggested topic";
    private static final int START_HOUR = 1;
    private static final int END_HOUR = 2;

    private static final Speech ADMIN_OR_MODERATOR_SPEECH_UPDATE = Speech.builder().withTopic(TOPIC).
            withSuggestedTopic(SUGGESTED_TOPIC)
            .withStartHour(START_HOUR).withEndHour(END_HOUR)
            .withVisitedPeople(0).withRegisteredPeople(0)
            .withSpeaker(User.builder().build())
            .withConference(Conference.builder().build())
            .build();

    private static final Speech SPEAKER_SPEECH_UPDATE = Speech.builder()
            .withVisitedPeople(0).withRegisteredPeople(0)
            .withSpeaker(User.builder().build())
            .withConference(Conference.builder().build())
            .build();
    @Mock
    private CrudPageableSpeechDao speechDao;

    @Mock
    private UserDao userDao;
    @Mock
    private CrudPageableConferenceDao conferenceDao;

    @InjectMocks
    SpeechServiceImpl speechService;


    @After
      public void resetMock(){
        reset(speechDao,userDao,conferenceDao);
    }

    @Test(expected = URLInjectionException.class)
      public void whenConferenceIdIdNotValidShouldBeException(){
        speechService.findAllSpeechesByConferenceId("string");
    }

    @Test
     public void updatingSpeechByModeratorOrSpeakerShouldWorkCorrect(){

        when(speechDao.findById(anyInt())).thenReturn(Optional.of(Speech.builder().build()));
        when(conferenceDao.getConferenceBySpeechId(anyInt())).thenReturn(Optional.of(Conference.builder().build()));
        when(userDao.getSpeakerOfSpeech(anyInt())).thenReturn(User.builder().build());
        when(speechDao.getMembersCount(anyInt())).thenReturn(0);

        Optional<Speech> speech = speechService.updateSpeech("1",TOPIC,SUGGESTED_TOPIC,"1","2");

        Assert.assertEquals(ADMIN_OR_MODERATOR_SPEECH_UPDATE,speech.get());
    }

    public void updatingSpeechBySpeakerShouldWorkCorrectly(){
        when(speechDao.findById(anyInt())).thenReturn(Optional.of(Speech.builder().build()));
        when(conferenceDao.getConferenceBySpeechId(anyInt())).thenReturn(Optional.of(Conference.builder().build()));
        when(userDao.getSpeakerOfSpeech(anyInt())).thenReturn(User.builder().build());
        when(speechDao.getMembersCount(anyInt())).thenReturn(0);

        Optional<Speech> speech = speechService.updateSpeech("1",null,SUGGESTED_TOPIC,null,null);

        Assert.assertEquals(SPEAKER_SPEECH_UPDATE,speech.get());
    }



}
