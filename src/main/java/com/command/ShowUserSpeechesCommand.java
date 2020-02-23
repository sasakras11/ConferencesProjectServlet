package com.command;

import com.context.AppContext;
import com.entity.User;
import com.service.SpeechService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowUserSpeechesCommand extends FrontCommand{

    private final SpeechService speechService;


    public ShowUserSpeechesCommand() {
        speechService = AppContext.getSpeechService();
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");
        req.setAttribute("speeches",speechService.getUserSpeeches(user.getUserId()));
          forward(user.getStatus().name().toLowerCase()+"/userSpeeches");
    }
}
