package com.command;

import com.context.AppContext;
import com.dao.ConferenceGroup;
import com.entity.User;
import com.service.ConferenceService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowConferencesComingCommand extends FrontCommand{

    private final ConferenceService conferenceService;


    public ShowConferencesComingCommand() {
        conferenceService = AppContext.CONFERENCE_SERVICE;
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        req.setAttribute("conferences",conferenceService.findAllConferences(1, ConferenceGroup.COMING));
        req.setAttribute("pageNum",1);
        forward(user.getStatus().name().toLowerCase()+"/conferencesComing");
    }
}
