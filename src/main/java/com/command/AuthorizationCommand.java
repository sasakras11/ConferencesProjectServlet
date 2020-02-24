package com.command;


import com.context.AppContext;
import com.dao.ConferenceGroup;
import com.entity.User;
import com.exception.ValidationException;
import com.service.ConferenceService;
import com.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class AuthorizationCommand extends FrontCommand {

    private final UserService userService;
    private final ConferenceService conferenceService;

    public AuthorizationCommand() {
        userService = AppContext.USER_SERVICE;
        conferenceService = AppContext.CONFERENCE_SERVICE;
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("loggingIn") != null) {
            login(req, resp);
        } else if (req.getParameter("registration") != null) {
            register(req, resp);
        } else {
            forward("start");
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Optional<User> user = userService.login(username, password);
        if (user.isPresent()) {
            HttpSession session = req.getSession();
            session.setAttribute("user", userService.findByName(username));
            req.setAttribute("conferences", conferenceService.findAllConferences(1, ConferenceGroup.COMING));
            req.setAttribute("pageNum", 1);
            forward(user.get().getStatus().name().toLowerCase() + "/conferencesComing");
        } else {
            req.setAttribute("error", "wrong credentials");
            forward("start");
        }
    }

    private void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = userService.register(req.getParameter("username"), req.getParameter("password"));
            req.getSession().setAttribute("user", user);
            req.setAttribute("conferences", conferenceService.findAllConferences(1, ConferenceGroup.COMING));
            req.setAttribute("pageNum", 1);
            forward(user.getStatus().name().toLowerCase() + "/conferencesComing");
        } catch (ValidationException e) {
            req.setAttribute("error", e.getMessage());
            forward("start");
        }
    }
}
