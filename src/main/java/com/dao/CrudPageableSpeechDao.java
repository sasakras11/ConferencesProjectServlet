package com.dao;

import com.entity.Speech;

import java.util.List;

public interface CrudPageableSpeechDao extends CrudPageableDao<Speech> {


    int count();

    List<Speech> getSpeechesByUserId(int userId);

    List<Speech> getSpeechesByConferenceId(int conferenceId);

    List<Speech> getSpeechesByUserIdAndConferenceId(int userId, int conferenceId);

    int getMembersCount(int speechId);

    boolean isRowPresentInSpeechIdUserIdRelation(int speechId, int userId);

    void insertIntoSpeechIdUserIdRelation(int speechId, int userId);

    List<Integer> getUserSpeechesIds(int userId);

    List<Speech> getUserSpeeches(int userId);

    void deleteFromSpeechIdUserIdRelation(int speechId, int userId);
}
