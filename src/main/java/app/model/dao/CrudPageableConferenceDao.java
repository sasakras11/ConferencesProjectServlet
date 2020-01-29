package app.model.dao;

import app.model.Conference;
import app.model.Speech;

import java.util.List;

public interface CrudPageableConferenceDao extends CrudPageableDao<Conference> {

    List<Conference> getConferences(boolean isEnded);
    List<Conference> getConferences(Page page,boolean isEnded);




    List<Speech> getSpeeches(int conference_id);

}
