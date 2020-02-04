package com.controller;


import com.command.impl.AuthorizationCommand;
import com.context.AppContext;
import com.dao.ConferenceGroup;
import com.entity.Role;
import com.entity.User;
import com.service.UserService;
import com.service.util.PasswordUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Servlet extends HttpServlet {

    private final UserService service;
    private final PasswordUtil passwordUtil;
    public Servlet() {
        service = AppContext.getUserService();
        passwordUtil = new PasswordUtil();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
             req.getRequestDispatcher("/pages/login.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        new AuthorizationCommand(service,passwordUtil,AppContext.getUserDao()).execute(req,resp);
    }
}