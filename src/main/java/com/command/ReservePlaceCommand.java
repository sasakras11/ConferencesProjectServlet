package com.command;

import com.context.AppContext;
import com.dao.CrudPageableConferenceDao;
import com.entity.Conference;
import com.entity.Speech;
import com.entity.User;
import com.service.ConferenceService;
import com.service.SpeechService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ReservePlaceCommand  extends FrontCommand{

    private final SpeechService speechService;
    private final ConferenceService conferenceService;


    public ReservePlaceCommand() {
        speechService = AppContext.getSpeechService();
        conferenceService = AppContext.getConferenceService();
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        User user = (User)req.getSession().getAttribute("user");
        speechService.reservePlace(req.getParameter("speechId"),String.valueOf(user.getUserId()));
        Optional<Speech> speech = speechService.findById(req.getParameter("speechId"));

        if(speech.isPresent()){
           Optional<Conference> conference = conferenceService.findConferenceBySpeechId(String.valueOf(speech.get().getId()));
            List<Speech> speeches = speechService.findAllSpeechesByConferenceId(String.valueOf(conference.get().getConferenceId()));
                  req.setAttribute("speeches",speeches);
                  req.setAttribute("userSpeechesIds",speechService.getUserSpeechesIds(user.getUserId()));
        }

        forward(user.getStatus().name().toLowerCase(),"speechesComing");
    }
}
