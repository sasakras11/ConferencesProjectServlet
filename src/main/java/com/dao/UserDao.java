package com.dao;

import com.entity.User;

import java.util.Optional;

public interface UserDao extends CrudDao<User>{

     Optional<User> findByUsername(String name);
}
