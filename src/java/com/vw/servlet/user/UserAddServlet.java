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
 * This servlet will add a user to the system
 * @author Adrián Ochoa Martínez
 */
@WebServlet("/UserAddServlet")
public class UserAddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        //this variable will contain the next page, depending on the result
        String nextPage;
        HttpSession session;
        
        //First we get the passwords field to check if they are equal, if they dont't, we send a message
        String password = request.getParameter("pass");
        String confirmedPassword = request.getParameter("confirm_pass");
        if (!password.equals(confirmedPassword)) {
            nextPage = "addUser.jsp";
            session = request.getSession();
            session.setAttribute("errorMessage", "Las contraseñas no coinciden.");
            request.getRequestDispatcher(nextPage).forward(request, response);
        }
        
        //If the password were equal, we get the rest of the fields
        String userName = request.getParameter("user");
        String rol = request.getParameter("tipo");
        String nombre = request.getParameter("nombre");
        String apP = request.getParameter("ap_p");
        String apM = request.getParameter("ap_m");
        String email = request.getParameter("email");
        String estatus = request.getParameter("estatus").toLowerCase();
        
        //creating the service
        UserService addUserService = new UserService();
        
        //if the user was created succesfuly, we redirect to the home page with a message of success.
        //Otherwise, we redirect to the error page
        if (addUserService.addUser(userName, password, rol, nombre, apP, apM, email, estatus)) {
            nextPage = "home.jsp";
            session = request.getSession();
            session.setAttribute("message", "El usuario se creó correctamente.");
        } else {
            nextPage = "error.jsp";
            session = request.getSession();
            session.setAttribute("errorMessage", "No se pudo crear el usuario.");
        }
        request.getRequestDispatcher(nextPage).forward(request, response);
    }
    
}
