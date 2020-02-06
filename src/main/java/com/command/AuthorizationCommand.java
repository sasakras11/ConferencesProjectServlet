package com.command;



import com.context.AppContext;
import com.dao.ConferenceGroup;
import com.dao.LocationCrudDao;
import com.dao.impl.CrudUserDaoImpl;
import com.entity.Conference;
import com.entity.User;
import com.service.UserService;
import com.service.util.Jsp.JspMap;
import com.service.util.Jsp.Stage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class AuthorizationCommand extends FrontCommand {

    private UserService service;
    private CrudUserDaoImpl userDao;
    private LocationCrudDao locationDao;
    public AuthorizationCommand() {
        service = AppContext.getUserService();
        userDao = AppContext.getUserDao();
        locationDao = AppContext.getLocationDao();
    }
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String action = req.getParameter("register");
        User user = null;
        HttpSession session = req.getSession();
        session.setAttribute("locale",new Locale("RU"));
        String destination = JspMap.getJspUrl(null, Stage.LOGIN);

        if (action == null) {
            if (service.login(username,password)) {
                user = userDao.findByUsername(username).get();
                session.setAttribute("user",user);
                destination = JspMap.getJspUrl(user.getStatus(),Stage.CONFERENCES_COMING);
            }

        } else {
            user = service.register(username,password);
            session.setAttribute("user",user);
            destination = JspMap.getJspUrl(user.getStatus(), Stage.CONFERENCES_COMING);

        }

        List<Conference> conferenceList = service.findAllConferences(1,ConferenceGroup.ALL);
        for (Conference conference: conferenceList
             ) {
            conference.setLocation(locationDao.findByConferenceId(conference.getConferenceId()));
        }
        session.setAttribute("conferences", conferenceList);


        forward(destination);
    }
    }

