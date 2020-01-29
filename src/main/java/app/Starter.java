package app;


import app.model.Conference;
import app.model.Location;
import app.model.Speech;
import app.model.dao.CrudPageableConferenceDao;
import app.model.dao.DataSource;
import app.model.dao.impl.CrudPageableDaoConferenceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

public class Starter {

    public static void main(String[] args) throws Exception{


   CrudPageableDaoConferenceImpl impl  = new CrudPageableDaoConferenceImpl("SELECT *FROM conferences WHERE conference_id = ?");

        System.out.println(impl.getSpeeches(1));

    }
}

