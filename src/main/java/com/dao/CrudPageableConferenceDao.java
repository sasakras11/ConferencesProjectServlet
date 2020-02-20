package com.dao;

import com.entity.Conference;

import java.util.List;
import java.util.Optional;

public interface CrudPageableConferenceDao extends CrudPageableDao<Conference> {


    List<Conference> findAll(int page,int itemsPerPage,ConferenceGroup group);

    public List<Conference> getConferencesByUserId(int userId);

     public int count(ConferenceGroup group);

     void update(Conference conference);

    Optional<Conference> getConferenceBySpeechId(int speechId);


}
