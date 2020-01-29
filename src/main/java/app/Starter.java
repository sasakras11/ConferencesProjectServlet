package app;

import app.model.Conference;
import app.model.dao.impl.CrudPageableDaoConferenceImpl;
import app.model.dao.impl.CrudUserDaoImpl;

import java.util.List;

public class Starter {

    public static void main(String[] args) throws Exception{


        CrudUserDaoImpl userDao = new CrudUserDaoImpl();
       List<Conference> conferences =  userDao.getUserConferences(5);
        for (Conference con: conferences
             ) {
            System.out.println(con.getName());
        }
    }
}

