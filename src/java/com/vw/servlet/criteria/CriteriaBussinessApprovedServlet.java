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
@WebServlet("/CriteriaBussinessApprovedServlet")
public class CriteriaBussinessApprovedServlet extends HttpServlet {
    
    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        String id = request.getParameter("id");
        System.out.println("id: " + id);
        String nextPage = "";
        HttpSession session = request.getSession();
        if(new CriteriaService().setBussinessApprovedCriteria(id)) {
            nextPage = "home.jsp";
            session.setAttribute("message", "Criterio aprobado");
        } else {
            nextPage = "error.jsp";
            session.setAttribute("errorMessage", "Ocurrió un error");
        }
        request.getRequestDispatcher(nextPage).forward(request, response);
    }
    
}
