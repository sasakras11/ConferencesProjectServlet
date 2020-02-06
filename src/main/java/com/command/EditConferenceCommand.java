package com.command;

import com.context.AppContext;
import com.dao.ConferenceGroup;
import com.dao.CrudPageableConferenceDao;
import com.dao.LocationCrudDao;
import com.entity.Conference;
import com.entity.Location;
import com.entity.User;
import com.service.util.Jsp.JspMap;
import com.service.util.Jsp.Stage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EditConferenceCommand extends FrontCommand {

    private LocationCrudDao locationDao;
    private CrudPageableConferenceDao conferenceDao;
    public EditConferenceCommand() {
        locationDao = AppContext.getLocationDao();
        conferenceDao = AppContext.getConferenceDao();
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("conferenceId"));
        int visitedPeople = Integer.parseInt(req.getParameter("visitedPeople"));
        int registeredPeople = Integer.parseInt(req.getParameter("registeredPeople"));
        String date = req.getParameter("date");
        String name = req.getParameter("name");

        Location location = locationDao.findByConferenceId(id);


        conferenceDao.update(
                Conference.builder().withId(id)
                        .withDate(date)
                        .withLocation(location)
                        .withName(name).build());


        HttpSession session = req.getSession();

        User user = (User)session.getAttribute("user");
        session.setAttribute("conferences",conferenceDao.findAll(1,5, ConferenceGroup.ALL));

        forward(JspMap.getJspUrl(user.getStatus(), Stage.CONFERENCES_COMING));

    }
}
