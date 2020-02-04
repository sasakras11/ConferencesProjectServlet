package com.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
;import java.io.IOException;

public interface Command {
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException;

    default void forward(String path, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(path).forward(request, response);
    }

}
