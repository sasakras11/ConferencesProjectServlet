package app.controller;


import app.model.dao.impl.CrudPageableDaoConferenceImpl;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class AppController {


   public void run() {

new CrudPageableDaoConferenceImpl("select *from conferences where conference_id = ?").getUserConferences(1);
   }
}