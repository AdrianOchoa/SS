/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vw.servlet.user;

import com.vw.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This class is a servlet that allows to deactivate an user
 * @author Adrián Ochoa Martínez
 */
@WebServlet("/UserDeleteServlet")
public class UserDeleteServlet extends HttpServlet {
    
    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        //this variable will contain the next page, depending on the result
        String nextPage;
        
        //Required field
        String userName = request.getParameter("user");
        
        //Creating the service
        UserService userService = new UserService();
        
        //if the user was successfully deleted, we redirect to the home page with a successful message.
        //Otherwise, we redirect to the error page
        HttpSession session;
        if(userService.deleteUser(userName)) {
            session = request.getSession();
            session.setAttribute("message", "Usuario eliminado correctamente.");
            nextPage = "home.jsp";
        } else {
            session = request.getSession();
            session.setAttribute("errorMessage", "No se pudo eliminar el usuario.");
            nextPage = "error.jsp";
        }
        request.getRequestDispatcher(nextPage).forward(request, response);
    }
    
}
