package com.service;

import com.entity.User;
import java.util.Optional;

public interface UserService {

    Optional<User> login(String username, String password);

    User register(String username,String password);


    User findByName(String name);


}
