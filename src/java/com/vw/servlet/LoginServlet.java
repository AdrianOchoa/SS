/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vw.servlet;

import com.vw.service.LogService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * this servlet will allow a user to use the system
 *
 * @author Adrián Ochoa Martínez
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //required fields
        String userName = request.getParameter("user");
        String password = request.getParameter("pass");

        //creating the log service
        LogService loginService = new LogService();

        //this variable will contain the next page, depending on the result
        String nextPage;

        //if the user is authenticated, it's session will be set to true and will be
        //redirected to the system. Otherwise it's session will be set to false and the system will send a message
        HttpSession session;
        if (loginService.authenticateUser(userName, password)) {
            session = request.getSession();
            session.setAttribute("logged", "true");
            session.setAttribute("message", "Bienvenido");
            session.setAttribute("rol", loginService.getUserRol());
            session.setAttribute("userID", loginService.getUserID());
            nextPage = "home.jsp";
        } else {
            session = request.getSession();
            session.setAttribute("errorMessage", "No se pudo iniciar sesión. Confirme que el usuario y contraseña son correctos.");
            nextPage = "login.jsp";
        }
        request.getRequestDispatcher(nextPage).forward(request, response);
    }
}
