package com.context;

import com.service.util.PasswordUtil;
import com.service.util.Validator;

public class AppContext {
    private static final PasswordUtil PASSWORD_UTIL = new PasswordUtil();
    private static final Validator VALIDATOR = new Validator();

}
