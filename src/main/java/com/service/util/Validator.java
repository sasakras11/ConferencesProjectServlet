package com.service.util;

import com.entity.User;
import com.exception.ValidationException;


public class Validator {

    private static final String PASSWORD_PATTERN = "[a-zA-Z0-9]{8,}";
    private static final String USERNAME_PATTERN = "[a-zA-Z0-9]{3,}";

    public void validate(String username,String password) {
        validateUsername(username);
        validatePassword(password);
    }

    private void validateUsername(String username) {
        validateString(username, USERNAME_PATTERN);
    }

    private void validatePassword(String password) {
        validateString(password, PASSWORD_PATTERN);
    }

    private void validateString(String str, String pattern) {
        if (!str.matches(pattern)) {
            throw new ValidationException(String.format("[%s] not matches pattern", str));
        }
    }
}
