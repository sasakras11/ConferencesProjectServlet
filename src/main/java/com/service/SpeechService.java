package com.service;

import com.entity.Speech;
import com.entity.User;

import java.util.List;
import java.util.Optional;

public interface SpeechService {

    List<Speech> findAllSpeechesByConferenceId(String conferenceId);

    User findSpeakerOfSpeech(int speechId);

    Optional<Speech> updateSpeech(String id, String topic, String suggestedTopic, String startHour, String endHour);

    Optional<Speech> findById(String id);

    void reservePlace(String speechId, String userId);

    List<Integer> getUserSpeechesIds(int userId);

    List<Speech> getUserSpeeches(int userId);

    void deleteReservation(String speechId,int  userId);


}
