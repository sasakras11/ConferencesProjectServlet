package com.service.util;

import com.entity.User;
import com.exception.ValidationException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class ValidatorTest {

    @Parameterized.Parameter
    public User user;

    Validator validator;

    @Parameterized.Parameters
    public static List<User> primeNumbers() {
        return Arrays.asList(new User.UserBuilder().withPassword("ale").withUsername("alexander_").build(),
                new User.UserBuilder().withPassword("alexa_dqdq").withUsername("ale?").build(),
                new User.UserBuilder().withPassword("aleADQDAS").withUsername("al!e").build(),
                new User.UserBuilder().withPassword("AAAAAVBB").withUsername("al()e").build(),
                new User.UserBuilder().withPassword("ACAWCAAC1").withUsername("ale№").build());
    }


    @Before
    public void init() {
        validator = new Validator();
    }

    @Test(expected = ValidationException.class)
    public void exceptionShouldBeIfNotMatchesPattern() {

        validator.validate(user.getUsername(),user.getPassword());

    }
}
