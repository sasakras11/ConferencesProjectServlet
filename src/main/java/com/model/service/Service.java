package com.model.service;

import com.model.dao.impl.CrudPageableDaoConferenceImpl;
import com.model.dao.impl.CrudPageableDaoSpeechImpl;
import com.model.dao.impl.CrudUserDaoImpl;
import com.model.dao.impl.LocationCrudDaoImpl;

public class Service {
    private final CrudPageableDaoConferenceImpl conferenceDao;
    private final CrudPageableDaoSpeechImpl speechDao;
    private final CrudUserDaoImpl userDao;
    private final LocationCrudDaoImpl locationDao;

    public Service(CrudPageableDaoConferenceImpl conferenceDao, CrudPageableDaoSpeechImpl speechDao, CrudUserDaoImpl userDao, LocationCrudDaoImpl locationDao) {
        this.conferenceDao = conferenceDao;
        this.speechDao = speechDao;
        this.userDao = userDao;
        this.locationDao = locationDao;
    }


}
