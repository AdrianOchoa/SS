/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vw.servlet.criteria;

import com.vw.service.CriteriaService;
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
@WebServlet("/CriteriaAdminApprovedServlet")
public class CriteriaAdminApprovedServlet extends HttpServlet {
    
    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        String nextPage = "";
        String id = request.getParameter("id");
        HttpSession session = request.getSession();
        if(new CriteriaService().setAdminApprovedCriteria(id)) {
            nextPage = "home.jsp";
            session.setAttribute("message", "Criterio aprobado");
        } else {
            nextPage = "error.jsp";
            session.setAttribute("errorMessage", "Error");
        }
        request.getRequestDispatcher(nextPage).forward(request, response);
    }
    
}
