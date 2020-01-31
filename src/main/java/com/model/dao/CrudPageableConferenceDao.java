package com.model.dao;

import com.model.entity.Conference;

import java.util.List;

public interface CrudPageableConferenceDao extends CrudPageableDao<Conference> {


    List<Conference> findAll(int page,int itemsPerPage,ConferenceGroup group);

    public List<Conference> getConferencesByUserId(int userId);




}
