package com.service.impl;

import com.dao.ConferenceGroup;
import com.dao.CrudPageableConferenceDao;
import com.dao.LocationCrudDao;
import com.entity.Conference;
import com.entity.Location;
import com.service.ConferenceService;

import java.util.List;
import java.util.Optional;

public class ConferenceServiceImpl extends AbstractService<Conference, CrudPageableConferenceDao> implements ConferenceService {

    private static final int ITEMS_PER_PAGE = 5;
    private final CrudPageableConferenceDao conferenceDao;
    private final LocationCrudDao locationDao;

    public ConferenceServiceImpl(CrudPageableConferenceDao conferenceDao, LocationCrudDao locationDao) {
        this.conferenceDao = conferenceDao;
        this.locationDao = locationDao;
    }

    @Override
    public void updateConference(Conference conference) {
        conferenceDao.update(conference);
    }

    @Override
    public Location findLocationOfConferenceId(int conferenceId) {
        return locationDao.findByConferenceId(conferenceId);
    }

    @Override
    public List<Conference> findAllConferences(int page, ConferenceGroup conferenceGroup) {
        List<Conference> conferences;
        int maxPage = conferenceDao.count(conferenceGroup) / ITEMS_PER_PAGE;
        if (page > maxPage) {
            conferences = conferenceDao.findAll(maxPage + 1, ITEMS_PER_PAGE, conferenceGroup);
        } else if (page < 1) {
            conferences = conferenceDao.findAll(1, ITEMS_PER_PAGE, conferenceGroup);
        } else {
            conferences = conferenceDao.findAll(page, ITEMS_PER_PAGE, conferenceGroup);
        }
        for (Conference conference : conferences) {
            conference.setLocation(findLocationOfConferenceId(conference.getConferenceId()));
        }

        return conferences;
    }

    @Override
    public Optional<Conference> findConferenceById(int conferenceId) {
        return conferenceDao.findById(conferenceId);
    }

    @Override
    public Optional<Conference> findConferenceBySpeechId(String speechId) {
        Optional<Integer> speechID = getParsedOctalNumberOrOptionalEmpty(speechId);
        if (speechID.isPresent()) {
            return conferenceDao.getConferenceBySpeechId(speechID.get());
        }
        return Optional.empty();
    }
}
