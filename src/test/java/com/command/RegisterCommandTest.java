package com.command;


import com.entity.User;
import com.exception.ValidationException;
import com.service.UserService;
import com.service.util.Validator;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RegisterCommandTest {

    public static final String USERNAME = "alex";
    public static final String PASSWORD = "pass";
    public static final String BAD_PASSWORD = "a";
    private static final User USER = User.builder().withUsername("USERNAME").withPassword(PASSWORD).build();


    @Mock
    ServletContext servletContext;
    @Mock
    RequestDispatcher requestDispatcher;
    @Mock
    Validator validator;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession httpSession;

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthorizationCommand registerCommand;


    @After
    public void resetMocks() {
        reset(request, response, httpSession, userService, validator, requestDispatcher, servletContext);
    }

    @Test
    public void registrationShouldBeSuccessfulIfCredentialsAreVerified() throws ServletException, IOException {
        when(request.getParameter("registration")).thenReturn("r");
        when(request.getParameter("username")).thenReturn(USERNAME);
        when(request.getParameter("password")).thenReturn(PASSWORD);
        when(userService.register(USERNAME, PASSWORD)).thenReturn(USER);
        when(request.getSession()).thenReturn(httpSession);
        doNothing().when(httpSession).setAttribute(any(), any());
        when(servletContext.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        doNothing().when(requestDispatcher).forward(any(), any());

        registerCommand.process(request, response);

        verify(request, times(4)).getParameter(any());

    }
}
