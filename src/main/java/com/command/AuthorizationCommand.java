package com.command;


import com.context.AppContext;
import com.dao.ConferenceGroup;
import com.entity.User;
import com.exception.ValidationException;
import com.service.UserService;
import com.service.util.Jsp.JspMap;
import com.service.util.Jsp.Stage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AuthorizationCommand extends FrontCommand {

    private UserService service;

    public AuthorizationCommand() {
        service = AppContext.getUserService();
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("register") == null) {
            login(req);
        } else {
            register(req);
        }
    }

    private void login(HttpServletRequest req) throws ServletException, IOException {
        Optional<User> user = service.login(req.getParameter("username"), req.getParameter("password"));

        if (user.isPresent()) {
            req.getSession().setAttribute("user", user.get());
            req.getSession().setAttribute("conferences", service.findAllConferences(1, ConferenceGroup.COMING));
            forward(JspMap.getJspUrl(user.get().getStatus(), Stage.CONFERENCES_COMING));
        } else {
            req.setAttribute("error", "wrong credentials");
            forward("start");
        }

    }

    private void register(HttpServletRequest req) throws ServletException, IOException {
        try {
            User user = service.register(req.getParameter("username"), req.getParameter("password"));
            req.getSession().setAttribute("user", user);
            req.setAttribute("conferences", service.findAllConferences(1, ConferenceGroup.COMING));
            forward(JspMap.getJspUrl(user.getStatus(), Stage.CONFERENCES_COMING));

        } catch (ValidationException e) {
            req.setAttribute("error", e.getMessage());
            forward("start");
        }
    }
}
