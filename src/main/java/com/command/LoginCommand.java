package com.command;

import com.context.AppContext;
import com.entity.User;
import com.service.UserService;
import com.service.util.Jsp.JspMap;
import com.service.util.Jsp.Stage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class LoginCommand extends  FrontCommand {

    private UserService service;

    public LoginCommand() {
        this.service = AppContext.getUserService();
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();

        Optional<User> user = service.login(username,password);
        if(user.isPresent()) {
            session.setAttribute("user",user.get());
            forward(JspMap.getJspUrl(user.get().getStatus(), Stage.CONFERENCES_COMING));
        }
        else {
           req.setAttribute("error","wrong credentials");
            forward("login");
        }

    }
}
