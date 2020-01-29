package app.model.dao.impl;

public class DaoContext {


    private static CrudPageableDaoSpeechImpl speechDao = new CrudPageableDaoSpeechImpl();
    private static CrudPageableDaoConferenceImpl conferenceDao = new CrudPageableDaoConferenceImpl();
    private static CrudUserDaoImpl userDao = new CrudUserDaoImpl();
    private static LocationCrudDaoImpl locationDao = new LocationCrudDaoImpl();

    public static CrudPageableDaoSpeechImpl getSpeechDao() {
        return speechDao;
    }

    public static void setSpeechDao(CrudPageableDaoSpeechImpl speechDao) {
        DaoContext.speechDao = speechDao;
    }

    public static CrudPageableDaoConferenceImpl getConferenceDao() {
        return conferenceDao;
    }

    public static void setConferenceDao(CrudPageableDaoConferenceImpl conferenceDao) {
        DaoContext.conferenceDao = conferenceDao;
    }

    public static CrudUserDaoImpl getUserDao() {
        return userDao;
    }

    public static void setUserDao(CrudUserDaoImpl userDao) {
        DaoContext.userDao = userDao;
    }

    public static LocationCrudDaoImpl getLocationDao() {
        return locationDao;
    }

    public static void setLocationDao(LocationCrudDaoImpl locationDao) {
        DaoContext.locationDao = locationDao;
    }
}
