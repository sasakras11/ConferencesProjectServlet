package com.command;

import com.context.AppContext;
import com.entity.Speech;
import com.entity.User;
import com.service.SpeechService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class SpeechEditCommand extends FrontCommand{

    private final SpeechService speechService;

    public SpeechEditCommand() {
        speechService = AppContext.getSpeechService();
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("speechId");
             String topic = req.getParameter("topic");
             String suggestedTopic = req.getParameter("suggestedTopic");
             String startHour = req.getParameter("startHour");
             String endHour = req.getParameter("endHour");
         Optional<Speech> speechOptional =     speechService.updateSpeech(id,topic,suggestedTopic,startHour,endHour);
        User user = (User) req.getSession().getAttribute("user");


         if(speechOptional.isPresent()){
             String conferenceId = String.valueOf(speechOptional.get().getConference().getConferenceId());
             req.setAttribute("speeches",speechService.findAllSpeechesByConferenceId(conferenceId));
             forward(user.getStatus().name().toLowerCase()+"/speechesComing");
         }
         else{
             req.setAttribute("speeches",speechService.findAllSpeechesByConferenceId("1"));
             forward(user.getStatus().name().toLowerCase()+"/conferencesComing");
         }
    }
}
