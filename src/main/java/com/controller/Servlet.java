package com.controller;



import com.context.AppContext;
import com.dao.ConferenceGroup;
import com.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Locale;

public class Servlet extends HttpServlet {


      UserService service = AppContext.getUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String registerForm = "pages/login.jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(registerForm);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String destination = "/pages/login.jsp";
      String action = req.getParameter("register");
           if(action==null){
             boolean isLoggedIn = service.login(req.getParameter("username"),req.getParameter("password"));
              if(isLoggedIn){
                   destination = "/pages/ConferencesList.jsp";
              }

           }

                    session.setAttribute("conferences",service.findAllConferences(1,4,ConferenceGroup.ALL));
           session.setAttribute("language",new Locale("RU"));
           RequestDispatcher dispatcher = req.getRequestDispatcher(destination);
            dispatcher.forward(req,resp);

    }
}
