package app;

import app.model.dao.impl.CrudPageableDaoConferenceImpl;

public class Starter {

    public static void main(String[] args) throws Exception{


       CrudPageableDaoConferenceImpl daoConference = new CrudPageableDaoConferenceImpl();
        daoConference.deleteById(3);
    }
}

