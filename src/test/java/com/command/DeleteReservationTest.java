package com.command;

import com.entity.Role;
import com.entity.User;
import com.service.ConferenceService;
import com.service.SpeechService;
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

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DeleteReservationTest {



    private static final User USER =User.builder().
            withUsername("username").withPassword("pass").withStatus(Role.ADMIN).build();

    @Mock
    ServletContext servletContext;
    @Mock
    RequestDispatcher requestDispatcher;
    @Mock
    SpeechService speechService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession httpSession;
    @Mock
    private UserService userService;

    @InjectMocks
    private DeleteReservationCommand deleteReservationCommand;


    @After
    public void resetMocks() {
        reset(request, response, httpSession, userService, requestDispatcher, servletContext, speechService);
    }


    @Test
    public void deleteReservation()throws Exception{
    when(request.getSession()).thenReturn(httpSession);
    when(httpSession.getAttribute("user")).thenReturn(USER);
    when(request.getParameter(any())).thenReturn("1");
    doNothing().when(request).setAttribute(any(),any());
        when(servletContext.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        doNothing().when(requestDispatcher).forward(any(), any());


       deleteReservationCommand.process(request, response);

        verify(request, times(1)).getParameter(any());
         verify(request, times(1)).setAttribute(any(),any());
        verify(httpSession, times(1)).getAttribute("user");

    }



}
