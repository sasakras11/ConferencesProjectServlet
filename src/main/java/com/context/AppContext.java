package com.context;

import com.dao.CrudPageableConferenceDao;
import com.dao.DataSource;
import com.dao.impl.*;
import com.service.impl.UserServiceImpl;
import com.service.util.PasswordUtil;
import com.service.util.Validator;

public class AppContext {
    private static final AppContext context = new AppContext();
    private static final DataSource DATA_SOURCE = new DataSource("C:\\Users\\sasak\\IdeaProjects\\Final_Servlet_Project\\src\\main\\resources\\db.properties");
    private static final PasswordUtil PASSWORD_UTIL = new PasswordUtil();
    private static final Validator VALIDATOR = new Validator();
    private static final CrudPageableDaoSpeechImpl SPEECH_DAO = new CrudPageableDaoSpeechImpl(DATA_SOURCE);
    private static final CrudPageableConferenceDao CONFERENCE_DAO = new CrudPageableDaoConferenceImpl(DATA_SOURCE);
    private static final CrudUserDaoImpl USER_DAO = new CrudUserDaoImpl(DATA_SOURCE);
    private static final LocationCrudDaoImpl LOCATION_DAO = new LocationCrudDaoImpl(DATA_SOURCE);
    private static final RatingDao RATING_DAO = new RatingDao(DATA_SOURCE);
    private static final UserServiceImpl USER_SERVICE = new UserServiceImpl(USER_DAO,VALIDATOR,CONFERENCE_DAO,PASSWORD_UTIL);


    private AppContext(){}

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
