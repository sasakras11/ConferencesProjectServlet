package com.service.impl;

import com.dao.CrudPageableConferenceDao;
import com.dao.CrudPageableSpeechDao;
import com.dao.UserDao;
import com.entity.Speech;
import com.entity.User;
import com.exception.URLInjectionException;
import com.service.SpeechService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class SpeechServiceImpl extends AbstractService<Speech, CrudPageableSpeechDao> implements SpeechService {

    private static final int ITEMS_PER_PAGE = 5;
    private CrudPageableSpeechDao speechDao;
    private UserDao userDao;
    private final CrudPageableConferenceDao conferenceDao;

    public SpeechServiceImpl(CrudPageableSpeechDao speechDao, UserDao userDao, CrudPageableConferenceDao conferenceDao) {
        this.speechDao = speechDao;
        this.userDao = userDao;
        this.conferenceDao = conferenceDao;
    }


    @Override
    public List<Speech> findAllSpeechesByConferenceId(String conferenceId) {

        Optional<Integer> conferenceID = getParsedOctalNumberOrOptionalEmpty(conferenceId);
        if (conferenceID.isPresent()) {
            List<Speech> speeches = speechDao.getSpeechesByConferenceId(conferenceID.get());
            for (Speech speech : speeches) {
                speech.setSpeaker(userDao.getSpeakerOfSpeech(speech.getId()));
                speech.setConference(conferenceDao.getConferenceBySpeechId(speech.getId()).get());
                speech.setRegisteredPeople(speechDao.getMembersCount(speech.getId()));

            }

            return speeches;
        }
        throw new URLInjectionException("start");

    }

    @Override
    public User findSpeakerOfSpeech(int speechId) {
        return userDao.getSpeakerOfSpeech(speechId);

    }

    @Override
    public Optional<Speech> updateSpeech(String id, String topic, String suggestedTopic, String startHour, String endHour) {

        Optional<Speech> optionalSpeech = findByIdIfPresentOrGetOptionalEmpty(id, speechDao);
        if (optionalSpeech.isPresent()) {

            Speech speech = optionalSpeech.get();

            getValidatedNameOrOptionalEmpty(topic).ifPresent(
                    speech::setTopic);
            getValidatedNameOrOptionalEmpty(suggestedTopic).ifPresent(
                    speech::setSuggestedTopic);
            getValidHourOrOptionalEmpty(startHour).ifPresent(
                    speech::setStartHour);
            getValidEndHourOrOptionalEmpty(startHour, endHour).ifPresent(
                    speech::setEndHour);
            speech.setConference(conferenceDao.getConferenceBySpeechId(speech.getId()).get());
            speech.setSpeaker(userDao.getSpeakerOfSpeech(speech.getId()));
            speech.setRegisteredPeople(speechDao.getMembersCount(speech.getId()));
            speech.setVisitedPeople(0);
            speechDao.update(speech);

            return Optional.of(speech);
        }
        return Optional.empty();
    }


    @Override
    public Optional<Speech> findById(String id) {
        return findByIdIfPresentOrGetOptionalEmpty(id, speechDao);
    }

    @Override
    public void reservePlace(String speechId, String userId) {
        Optional<Integer> speechID = getParsedOctalNumberOrOptionalEmpty(speechId);
        Optional<Integer> userID = getParsedOctalNumberOrOptionalEmpty(userId);

        if (speechID.isPresent()
                && userID.isPresent()
                && !speechDao.isRowPresentInSpeechIdUserIdRelation(speechID.get(), userID.get())) {

            speechDao.insertIntoSpeechIdUserIdRelation(speechID.get(), userID.get());
        }
    }
     @Override
    public List<Integer> getUserSpeechesIds(int userId){

            return speechDao.getUserSpeechesIds(userId);

    }

    @Override
     public List<Speech> getUserSpeeches(int  userId){
            return speechDao.getUserSpeeches(userId);

     }

     @Override
       public void deleteReservation(String speechId,int userId){
        Optional<Integer> speechID = getParsedOctalNumberOrOptionalEmpty(speechId);
         speechID.ifPresent(integer -> speechDao.deleteFromSpeechIdUserIdRelation(integer, userId));
    }
}
