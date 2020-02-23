package com.command;

import com.context.AppContext;
import com.entity.Conference;
import com.entity.User;
import com.service.ConferenceService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowConferenceEditPageCommand extends FrontCommand {

    private final ConferenceService conferenceService;
    public ShowConferenceEditPageCommand() {
        this.conferenceService = AppContext.getConferenceService();
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("conferenceId"));
        Conference conference = conferenceService.findConferenceById(id).get();

        User user = (User) req.getSession().getAttribute("user");
       req.setAttribute("conference", conference);
       forward(user.getStatus().name().toLowerCase()+"/conferenceEdit");
   }
}
