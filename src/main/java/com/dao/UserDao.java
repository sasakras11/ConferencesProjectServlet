package com.dao;

import com.entity.Role;
import com.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends CrudDao<User>{

     Optional<User> findByUsername(String name);

     List<User> findByRole(Role role);

     User getSpeakerOfSpeech(int speechId);
}
