package com.service.impl;

import com.dao.UserDao;
import com.entity.Role;
import com.entity.User;
import com.exception.ValidationException;
import com.service.UserService;
import com.service.util.PasswordUtil;
import com.service.util.Validator;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private Validator validator;
    private PasswordUtil passwordUtil;

    public UserServiceImpl(UserDao userDao, Validator validator,PasswordUtil passwordUtil) {
        this.userDao = userDao;
        this.validator = validator;
        this.passwordUtil = passwordUtil;
    }

    @Override
    public Optional<User> login(String username, String password) {

        Optional<User> byUsername = userDao.findByUsername(username);
        String hashedPassword = passwordUtil.getHashedPassword(password);

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
}