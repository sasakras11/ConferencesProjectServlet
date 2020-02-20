package com.command;


import com.context.AppContext;
import com.dao.ConferenceGroup;
import com.entity.User;
import com.exception.ValidationException;
import com.service.ConferenceService;
import com.service.UserService;
import com.service.jsp.JspMap;
import com.service.jsp.Stage;


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

        if (req.getParameter("register") == null) {
            login(req);
        } else {
            register(req);
        }
    }

    private void login(HttpServletRequest req) throws ServletException, IOException {

        Optional<User> user = userService.login(req.getParameter("username"), req.getParameter("password"));
        if (user.isPresent()) {
            HttpSession session = req.getSession();
          session.setAttribute("user", user.get());
           req.getSession().setAttribute("conferences",conferenceService.findAllConferences(1, ConferenceGroup.COMING));
            req.setAttribute("pageNum",1);
            forward(user.get().getStatus().name().toLowerCase(),"conferencesComing");
        } else {
            req.setAttribute("error", "wrong credentials");
            forward("start");
        }

    }

    private void register(HttpServletRequest req) throws ServletException, IOException {
        try {
            User user = userService.register(req.getParameter("username"), req.getParameter("password"));
            req.getSession().setAttribute("user", user);
            req.setAttribute("conferences",conferenceService.findAllConferences(1, ConferenceGroup.COMING));
            req.setAttribute("pageNum",1);
            forward(user.getStatus().name().toLowerCase(),"conferencesComing");

        } catch (ValidationException e) {
            req.setAttribute("error", e.getMessage());
            forward("start");
        }
    }
}
