package com.service.impl;

import com.dao.ConferenceGroup;
import com.dao.CrudPageableConferenceDao;
import com.dao.UserDao;
import com.entity.Conference;
import com.entity.User;
import com.service.UserService;
import com.service.util.PasswordUtil;
import com.service.util.Validator;

import java.util.List;

public class UserServiceImpl  implements UserService {

    private UserDao userDao;
    private Validator validator;
    private CrudPageableConferenceDao conferenceDao;
    private static final int ITEMS_PER_PAGE = 5;


    public UserServiceImpl(UserDao userDao, Validator validator, CrudPageableConferenceDao conferenceDao) {
        this.userDao = userDao;
        this.validator = validator;
        this.conferenceDao = conferenceDao;

    }


    @Override
    public boolean login(String username, String password) {


        String hashedPassword = PasswordUtil.getHashedPassword(password);
        return userDao.findByUsername(username)
                .map(User::getPassword)
               .filter(pass -> pass.equals(hashedPassword))
               .isPresent();
    }

    @Override
    public void register(User user) {
        validator.validate(user);

        userDao.findByUsername(user.getUsername()).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Conference> findAllConferences(int page, int itemsPerPage, ConferenceGroup conferenceGroup) {


        if (conferenceDao.count(conferenceGroup) / itemsPerPage < page) {

            return conferenceDao.findAll(1, ITEMS_PER_PAGE, conferenceGroup);
        } else if (page < 1) {

            return conferenceDao.findAll(1, ITEMS_PER_PAGE, conferenceGroup);

        } else return conferenceDao.findAll(page, itemsPerPage, conferenceGroup);

    }
}