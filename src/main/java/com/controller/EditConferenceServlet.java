package com.controller;

import com.context.AppContext;
import com.dao.CrudPageableConferenceDao;
import com.dao.impl.CrudPageableDaoConferenceImpl;
import com.entity.Role;
import com.entity.User;
import com.service.util.Jsp.JspMap;
import com.service.util.Jsp.Stage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.io.IOException;

public class EditConferenceServlet extends HttpServlet {


    CrudPageableConferenceDao conferenceDao;


    public EditConferenceServlet() {
        this.conferenceDao = AppContext.getConferenceDao();
    }

    @Override //TODO тут я ловлю запит на сторінку редагування conference і даю сторінку для редагування
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int conferenceId = (int)req.getAttribute("conferenceId");
          HttpSession session = req.getSession();
          User user =(User) session.getAttribute("user");
           session.setAttribute("conferenceToEdit",conferenceDao.findById(conferenceId));

          resp.sendRedirect("conferenceEdit.jsp");
     }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
