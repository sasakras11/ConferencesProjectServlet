package com.service;

import com.dao.ConferenceGroup;
import com.entity.Conference;
import com.entity.Location;

import java.util.List;
import java.util.Optional;

public interface ConferenceService {

     void updateConference(Conference conference);

     Location findLocationOfConferenceId(int conferenceId);

    List<Conference> findAllConferences(int page, ConferenceGroup conferenceGroup);

    Optional<Conference> findConferenceById(int conferenceId);

    Optional<Conference> findConferenceBySpeechId(String speechId);

}
