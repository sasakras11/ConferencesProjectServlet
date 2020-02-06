package com.command;

import com.context.AppContext;
import com.dao.CrudPageableSpeechDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookPlaceCommand extends FrontCommand{

       private CrudPageableSpeechDao speechDao;

    public BookPlaceCommand() {
         speechDao = AppContext.getSpeechDao();
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        //TODO if user already booked place should return message


    }
}
