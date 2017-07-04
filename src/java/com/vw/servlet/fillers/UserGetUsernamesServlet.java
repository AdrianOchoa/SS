/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vw.servlet.fillers;

import com.vw.service.UserService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This servlet will provide the active usernames in the system. It's used in the userDelete jsp.
 * @author Adrián Ochoa Martínez
 */
@WebServlet("/UserGetUsernamesServlet")
public class UserGetUsernamesServlet extends HttpServlet {
    
    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        String userID = (String) session.getAttribute("userID");
        List<String> users = new UserService().getActiveUserNames(userID);
        session.setAttribute("users", users);
    }
    
}