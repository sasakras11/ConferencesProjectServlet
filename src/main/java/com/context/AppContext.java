package com.context;

import com.dao.CrudPageableConferenceDao;
import com.dao.DataSource;
import com.dao.impl.*;
import com.service.ConferenceService;
import com.service.SpeechService;
import com.service.impl.ConferenceServiceImpl;
import com.service.impl.SpeechServiceImpl;
import com.service.impl.UserServiceImpl;
import com.service.util.PasswordUtil;
import com.service.util.Validator;

public class AppContext {

    private static final PasswordUtil PASSWORD_UTIL = new PasswordUtil();
    private static final Validator VALIDATOR = new Validator();
    private static final CrudPageableDaoSpeechImpl SPEECH_DAO = new CrudPageableDaoSpeechImpl();
    public  static final CrudPageableConferenceDao CONFERENCE_DAO = new CrudPageableDaoConferenceImpl();
    private static final CrudUserDaoImpl USER_DAO = new CrudUserDaoImpl();
    private static final LocationCrudDaoImpl LOCATION_DAO = new LocationCrudDaoImpl();
    public  static final ConferenceService CONFERENCE_SERVICE = new ConferenceServiceImpl(CONFERENCE_DAO, LOCATION_DAO);
    public static final UserServiceImpl USER_SERVICE = new UserServiceImpl(USER_DAO, VALIDATOR, PASSWORD_UTIL);
    private static final SpeechService SPEECH_SERVICE = new SpeechServiceImpl(SPEECH_DAO, USER_DAO,CONFERENCE_DAO);

    private AppContext() { }

    public static ConferenceService getConferenceService() {
        return  CONFERENCE_SERVICE;
    }

    public static SpeechService getSpeechService() {
        return SPEECH_SERVICE;
    }

    public static PasswordUtil getPasswordUtil() {
        return PASSWORD_UTIL;
    }

    public static CrudPageableDaoSpeechImpl getSpeechDao() {
        return SPEECH_DAO;
    }

    public static CrudUserDaoImpl getUserDao() {
        return new CrudUserDaoImpl();
    }

    public static CrudPageableConferenceDao getConferenceDao() {
        return CONFERENCE_DAO;
    }

}
