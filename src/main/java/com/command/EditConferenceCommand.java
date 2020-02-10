package com.command;

import com.context.AppContext;
import com.dao.ConferenceGroup;
import com.dao.CrudPageableConferenceDao;
import com.dao.LocationCrudDao;
import com.entity.Conference;
import com.entity.Location;
import com.entity.User;
import com.service.ConferenceService;
import com.service.impl.ConferenceServiceImpl;
import com.service.util.Jsp.JspMap;
import com.service.util.Jsp.Stage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EditConferenceCommand extends FrontCommand {

   ConferenceService conferenceService;

    public EditConferenceCommand() {
        conferenceService = AppContext.getConferenceService();
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        int id = Integer.parseInt(req.getParameter("conferenceId"));
        String date = req.getParameter("date");
        String name = req.getParameter("name");

        Location location = conferenceService.findLocationOfConferenceId(id);

        conferenceService.updateConference(
                Conference.builder().withId(id)
                        .withDate(date)
                        .withLocation(location)
                        .withName(name).build());

        session.setAttribute("conferences", conferenceService.findAllConferences(1, ConferenceGroup.ALL));

        forward(JspMap.getJspUrl(user.getStatus(), Stage.CONFERENCES_COMING));

    }
}
