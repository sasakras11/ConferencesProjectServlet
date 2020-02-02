package com.service.util;

import com.entity.User;
import com.exception.ValidationException;


public class Validator {

    private static final String PASSWORD_PATTERN ="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
    private static final String USERNAME_PATTERN = "^[a-z0-9_-]{3,16}$";


    public void validate(User user){
        if(user==null){
            throw new ValidationException("user is null");
        }
        validateUsername(user.getUsername());
        validatePassword(user.getPassword());


    }


    private void validateUsername(String username){
                   validateString(username,USERNAME_PATTERN);
    }
    private void validatePassword(String password){
     validateString(password,PASSWORD_PATTERN);
    }
  private void validateString(String str, String pattern){
        if(!str.matches(pattern)){
             throw new ValidationException(String.format("[%s] not matches pattern",str));
        }
    }
}
