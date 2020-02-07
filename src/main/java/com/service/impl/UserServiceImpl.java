package com.service.impl;

import com.dao.ConferenceGroup;
import com.dao.CrudPageableConferenceDao;
import com.dao.UserDao;
import com.entity.Conference;
import com.entity.Role;
import com.entity.User;
import com.exception.ValidationException;
import com.service.UserService;
import com.service.util.PasswordUtil;
import com.service.util.Validator;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final int ITEMS_PER_PAGE = 5;
    private UserDao userDao;
    private Validator validator;
    private CrudPageableConferenceDao conferenceDao;
    private PasswordUtil passwordUtil;

    public UserServiceImpl(UserDao userDao, Validator validator, CrudPageableConferenceDao conferenceDao, PasswordUtil passwordUtil) {
        this.userDao = userDao;
        this.validator = validator;
        this.conferenceDao = conferenceDao;
        this.passwordUtil = passwordUtil;
    }

    @Override
    public Optional<User> login(String username, String password) {
        String hashedPassword = passwordUtil.getHashedPassword(password);

        Optional<User> byUsername = userDao.findByUsername(username);

        boolean isPresent = byUsername
                .map(User::getPassword)
                .filter(pass -> pass.equals(hashedPassword))
                .isPresent();


        return isPresent ? byUsername : Optional.empty();
    }

    @Override
    public User register(String username, String password) {
        validator.validate(username, password);

        if (!(userDao.findByUsername(username).isPresent())) {
            User user = User.builder().withUsername(username).withPassword(passwordUtil.getHashedPassword(password)).withStatus(Role.VISITOR).build();
            userDao.save(user);
            return user;

        } else {
            throw new ValidationException("user is registered");
        }
    }

    @Override
    public List<Conference> findAllConferences(int page, ConferenceGroup conferenceGroup) {

        int maxPage = conferenceDao.count(conferenceGroup) / ITEMS_PER_PAGE;
        if (maxPage < page) {
            return conferenceDao.findAll(maxPage + 1, ITEMS_PER_PAGE, conferenceGroup);
        }
        if (page < 1) {
            return conferenceDao.findAll(1, ITEMS_PER_PAGE, conferenceGroup);
        }
        return conferenceDao.findAll(page, ITEMS_PER_PAGE, conferenceGroup);

    }
}