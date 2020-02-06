package com.command;

import com.context.AppContext;
import com.dao.CrudPageableSpeechDao;
import com.dao.UserDao;
import com.dao.impl.CrudPageableDaoSpeechImpl;
import com.dao.impl.CrudUserDaoImpl;
import com.entity.Role;
import com.entity.Speech;
import com.entity.User;
import com.service.util.Jsp.JspMap;
import com.service.util.Jsp.Stage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowSpeechesCommand extends FrontCommand {

    private UserDao userDao;
    private CrudPageableSpeechDao speechDao;
    public ShowSpeechesCommand() {
        speechDao = AppContext.getSpeechDao();
        userDao = AppContext.getUserDao();
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

           List<Speech> speeches = speechDao.getSpeechesByConferenceId(Integer.parseInt(req.getParameter("id")));
           req.getSession().setAttribute("speeches",speeches);

           User user = (User)req.getSession().getAttribute("user");

           List<User> speakers = userDao.findByRole(Role.SPEAKER);

        for (Speech speech: speeches
             ) {
            speech.setSpeaker(userDao.getSpeakerOfSpeech(speech.getId()));
        }
            req.getSession().setAttribute("speakers",speakers);

           forward(JspMap.getJspUrl(user.getStatus(), Stage.SPEECHES));

    }
}
