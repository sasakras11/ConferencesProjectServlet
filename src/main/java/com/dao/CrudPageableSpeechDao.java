package com.dao;

import com.entity.Speech;
import com.entity.User;

import java.util.List;

public interface CrudPageableSpeechDao extends CrudPageableDao<Speech> {


    int count();
    public List<Speech> getSpeechesByUserId(int userId);
    public List<Speech> getSpeechesByConferenceId(int conferenceId);
    public List<Speech> getSpeechesByUserIdAndConferenceId(int userId,int conferenceId);
    int getMembersCount(int speechId);

}
