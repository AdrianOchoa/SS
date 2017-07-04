/*
 * To change this template, choose Tools | Templates
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
 * @author Adrián Ochoa Martínez
 */
@WebServlet("/CriteriaDeleteServlet")
public class CriteriaDeleteServlet extends HttpServlet {
    
    @Override 
    protected void doPost (HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        //this variable will contain the next page, depending on the result
        String nextPage;
        
        //Required field
        String criteriaID = request.getParameter("criterio");
        
        //Creating the service
        CriteriaService criteriaService = new CriteriaService();
        
        //if the user was successfully deleted, we redirect to the home page with a successful message.
        //Otherwise, we redirect to the error page
        HttpSession session;
        if(criteriaService.deleteCriteria(criteriaID)) {
            session = request.getSession();
            session.setAttribute("message", "Criterio eliminado correctamente.");
            nextPage = "home.jsp";
        } else {
            session = request.getSession();
            session.setAttribute("errorMessage", "No se pudo eliminar el criterio.");
            nextPage = "error.jsp";
        }
        request.getRequestDispatcher(nextPage).forward(request, response);
    }
    
}
