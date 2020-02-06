package com.command;

import com.service.util.Jsp.JspMap;
import com.service.util.Jsp.Stage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UnknownCommand extends FrontCommand {


    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            forward("start");
    }
}