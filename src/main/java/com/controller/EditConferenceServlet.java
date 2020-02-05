package com.controller;

import com.context.AppContext;
import com.dao.ConferenceGroup;
import com.dao.CrudDao;
import com.dao.CrudPageableConferenceDao;
import com.dao.LocationCrudDao;
import com.dao.impl.LocationCrudDaoImpl;
import com.entity.Conference;
import com.entity.Location;
import com.entity.User;
import com.service.util.Jsp.JspMap;
import com.service.util.Jsp.Stage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EditConferenceServlet extends HttpServlet {


    CrudPageableConferenceDao conferenceDao;
   LocationCrudDao  locationDao;


    public EditConferenceServlet() {
        this.conferenceDao = AppContext.getConferenceDao();
        locationDao = AppContext.getLocationDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        Conference conference = conferenceDao.findById(id).get();
        RequestDispatcher dispatcher = req.getRequestDispatcher("pages/conferenceEdit.jsp");
        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("conferenceToEdit", conference);
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("conferenceId"));
        int visitedPeople = Integer.parseInt(req.getParameter("visitedPeople"));
        int registeredPeople = Integer.parseInt(req.getParameter("registeredPeople"));
        String date = req.getParameter("date");
        String name = req.getParameter("name");

       Location location = locationDao.findByConferenceId(id);


        conferenceDao.update(
                Conference.builder().withId(id)
                        .withVisitedPeople(visitedPeople)
                        .withRegisteredPeople(registeredPeople)
                        .withDate(date)
                        .withLocation(location)
                        .withName(name).build());


        HttpSession session = req.getSession();

        User user = (User)session.getAttribute("user");
        session.setAttribute("conferences",conferenceDao.findAll(1,5, ConferenceGroup.ALL));

        System.out.println(req.getRequestURL());
        System.out.println(req.getPathInfo());
        System.out.println(req.getContextPath());
        System.out.println(req.getRequestURI());

      //  req.getRequestDispatcher(JspMap.getJspUrl(user.getStatus(), Stage.CONFERENCES_COMING)).forward(req,resp);
    }




}
