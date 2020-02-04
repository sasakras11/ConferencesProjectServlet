package com.command.impl;

import com.command.Command;
import com.dao.ConferenceGroup;
import com.dao.impl.CrudUserDaoImpl;
import com.entity.Role;
import com.entity.User;
import com.service.UserService;
import com.service.util.Jsp.JspMap;
import com.service.util.Jsp.Stage;
import com.service.util.PasswordUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class AuthorizationCommand implements Command {

    UserService service;
    PasswordUtil passwordUtil;
    CrudUserDaoImpl userDao;


    public AuthorizationCommand(UserService service, PasswordUtil util, CrudUserDaoImpl userDao) {
        this.service = service;
        this.passwordUtil = util;
        this.userDao = userDao;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String action = req.getParameter("register");
        User user = null;
        HttpSession session = req.getSession();
        String destination = JspMap.getJspUrl(null,Stage.LOGIN);

        if (action == null) {
            if (service.login(username,password)) {
                user = userDao.findByUsername(username).get();
                session.setAttribute("user",user);
                destination = JspMap.getJspUrl(user.getStatus(),Stage.CONFERENCES_COMING);
                session.setAttribute("conferences", service.findAllConferences(1, ConferenceGroup.COMING));
            }

        } else {

           user = service.register(username,password);
            session.setAttribute("user",user);
            session.setAttribute("conferences", service.findAllConferences(1, ConferenceGroup.COMING));
            destination = JspMap.getJspUrl(user.getStatus(), Stage.CONFERENCES_COMING);

        }

        resp.sendRedirect(destination);
     }
}
