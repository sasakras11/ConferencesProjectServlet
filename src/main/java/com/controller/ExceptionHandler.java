package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static javax.servlet.RequestDispatcher.*;

public class ExceptionHandler extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    process(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req,resp);
    }

    public void process(HttpServletRequest req, HttpServletResponse resp)throws IOException{


            resp.setContentType("text/html; charset=utf-8");
            try (PrintWriter writer = resp.getWriter()) {
                writer.write("<html><head><title>Error description</title></head><body>");
                writer.write("<h2>Error description</h2>");
                writer.write("<a href =\"/\">go to login</a>");
                writer.write("<ul>");
                Arrays.asList(
                        ERROR_STATUS_CODE,
                        ERROR_EXCEPTION_TYPE,
                        ERROR_MESSAGE)
                        .forEach(e ->
                                writer.write("<li>" + e + ":" + req.getAttribute(e) + " </li>")
                        );
                writer.write("</ul>");
                writer.write("</html></body>");
            }
        }
    }
