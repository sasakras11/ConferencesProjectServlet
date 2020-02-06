package com.controller;

import com.command.FrontCommand;
import com.command.UnknownCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        FrontCommand command = getCommand(request);       // дістаємо команду
        command.init(getServletContext(), request, response);      // даємо їй параметри
        command.process(request,response);    //виконуэмо
    }

    private FrontCommand getCommand(HttpServletRequest request) {
        try {

            System.out.println(request.getParameter("command"));
            Class type = Class.forName(String.format(
                    "com.command.%sCommand",
                    request.getParameter("command")));
            return (FrontCommand) type
                    .asSubclass(FrontCommand.class)
                    .newInstance();
        } catch (Exception e) {
            return new UnknownCommand();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FrontCommand command = getCommand(req);       // дістаємо команду
        command.init(getServletContext(), req, resp);      // даємо їй параметри
        command.process(req,resp);
    }
}