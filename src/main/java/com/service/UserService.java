package com.service;

import com.dao.ConferenceGroup;
import com.entity.Conference;
import com.entity.User;

import java.util.List;

public interface UserService {


    boolean login(String username,String password);


    User register(String username,String password);


    List<Conference> findAllConferences(int page,ConferenceGroup conferenceGroup);
}
