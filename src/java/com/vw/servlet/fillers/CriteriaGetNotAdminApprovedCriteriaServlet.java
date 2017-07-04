/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vw.servlet.fillers;

import com.vw.service.CriteriaService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet will provide a list with the id's of the not yet approved criteria
 * @author Adrián Ochoa Martínez
 */
@WebServlet("/CriteriaGetNotAdminApprovedCriteriaServlet")
public class CriteriaGetNotAdminApprovedCriteriaServlet extends HttpServlet {
    
    @Override 
    protected void doGet (HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        List<String> criterios = new CriteriaService().getNotAdminApprovedCriteriaById();
        request.getSession().setAttribute("criterios", criterios);
    }
    
}
