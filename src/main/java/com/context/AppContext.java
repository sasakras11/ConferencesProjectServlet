package com.context;

import com.dao.CrudPageableConferenceDao;
import com.dao.DataSource;
import com.dao.impl.*;
import com.entity.User;
import com.service.ConferenceService;
import com.service.SpeechService;
import com.service.impl.ConferenceServiceImpl;
import com.service.impl.SpeechServiceImpl;
import com.service.impl.UserServiceImpl;
import com.service.util.PasswordUtil;
import com.service.util.Validator;

public class AppContext {
    private static final AppContext context = new AppContext();
    private static final DataSource DATA_SOURCE = new DataSource("/home/alex/IdeaProjects/Final_Servlet_Project/src/main/resources/db.properties");
    private static final PasswordUtil PASSWORD_UTIL = new PasswordUtil();
    private static final Validator VALIDATOR = new Validator();
    private static final CrudPageableDaoSpeechImpl SPEECH_DAO = new CrudPageableDaoSpeechImpl(DATA_SOURCE);
    private static final CrudPageableConferenceDao CONFERENCE_DAO = new CrudPageableDaoConferenceImpl(DATA_SOURCE);
    private static final CrudUserDaoImpl USER_DAO = new CrudUserDaoImpl(DATA_SOURCE);
    private static final LocationCrudDaoImpl LOCATION_DAO = new LocationCrudDaoImpl(DATA_SOURCE);
    private static final RatingDao RATING_DAO = new RatingDao(DATA_SOURCE);
    private static final UserServiceImpl USER_SERVICE = new UserServiceImpl(USER_DAO,VALIDATOR,CONFERENCE_DAO,PASSWORD_UTIL);
    public static final ConferenceService CONFERENCE_SERVICE = new ConferenceServiceImpl(CONFERENCE_DAO,LOCATION_DAO);
    private static final SpeechService SPEECH_SERVICE = new SpeechServiceImpl(SPEECH_DAO, USER_DAO);

    private AppContext(){}

    public static ConferenceService getConferenceService() {
        return CONFERENCE_SERVICE;
    }

    public static SpeechService getSpeechService() {
        return SPEECH_SERVICE;
    }

    public static LocationCrudDaoImpl getLocationDao() {
        return LOCATION_DAO;
    }

    public static PasswordUtil getPasswordUtil() {
        return PASSWORD_UTIL;
    }

    public static CrudPageableDaoSpeechImpl getSpeechDao() {
        return SPEECH_DAO;
    }

    public static CrudUserDaoImpl getUserDao(){
        return USER_DAO;
    }
    public static AppContext getInstance(){
        return context;
    }

    public static UserServiceImpl getUserService() {
        return USER_SERVICE;
    }

    public static CrudPageableConferenceDao getConferenceDao() {
        return CONFERENCE_DAO;
    }

}
