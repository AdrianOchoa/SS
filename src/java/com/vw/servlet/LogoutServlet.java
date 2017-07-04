/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vw.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Adrián Ochoa Martínez
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
//        request.getSession().setAttribute("logged", false);
//        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        session.setAttribute("logged", null);
        session.setAttribute("message", null);
        session.setAttribute("rol", null);
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
