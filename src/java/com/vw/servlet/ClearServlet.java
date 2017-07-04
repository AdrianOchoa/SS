/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
 * @author Adrián
 */
@WebServlet("/ClearServlet")
public class ClearServlet extends HttpServlet {
    
    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        Boolean flag = (Boolean) session.getAttribute("flag");
        if(flag == null || flag == false) {
            session.setAttribute("result", null);
            session.setAttribute("message", null);
            session.setAttribute("errorMessage", null);
            session.setAttribute("flag", true);
        }
    }
    
}
