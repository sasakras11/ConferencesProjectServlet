package com.command;

import com.entity.Role;
import com.entity.User;
import com.service.ConferenceService;
import com.service.UserService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LoginCommandTest {

    public static final String USERNAME = "alex";
    public static final String PASSWORD = "secret";
    private static final Optional<User> USER = Optional.of(User.builder().withUsername(USERNAME).withPassword(PASSWORD).withStatus(Role.ADMIN).build());

    @Mock
    ServletContext servletContext;
    @Mock
    RequestDispatcher requestDispatcher;
    @Mock
    ConferenceService conferenceService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession httpSession;
    @Mock
    private UserService userService;

    @InjectMocks
    private AuthorizationCommand loginCommand;


    @After
    public void resetMocks() {
        reset(request, response, httpSession, userService, requestDispatcher, servletContext, conferenceService);
    }

    @Test
    public void executeShouldLoginUser() throws Exception {
        when(request.getParameter("username")).thenReturn(USERNAME);
        when(request.getParameter("password")).thenReturn(PASSWORD);
        when(userService.login(USERNAME, PASSWORD)).thenReturn(USER);
        doNothing().when(request).setAttribute(any(), any());
        when(servletContext.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        doNothing().when(requestDispatcher).forward(any(), any());


        loginCommand.process(request, response);

        verify(request, times(3)).getParameter(any());
    }
    @Test
    public void ifUserIsNotPresentHeShouldBeOnStartPage()throws Exception{
        when(request.getParameter("username")).thenReturn(USERNAME);
        when(request.getParameter("password")).thenReturn(PASSWORD);
        when(userService.login(USERNAME, PASSWORD)).thenReturn(Optional.empty());
        doNothing().when(request).setAttribute(any(),any());
        when(servletContext.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        doNothing().when(requestDispatcher).forward(any(), any());


        loginCommand.process(request, response);

        verify(request, times(3)).getParameter(any());



    }
}
