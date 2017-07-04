/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vw.servlet.criteria;

import com.vw.model.Criteria;
import com.vw.service.CriteriaService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This servlet it's used in the criteriaList jsp.
 * @author Adrián Ochoa Martínez
 */
@WebServlet("/CriteriaGetCriteriaServlet")
public class CriteriaGetCriteriaServlet extends HttpServlet {
    
    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        List<Criteria> criterios = new CriteriaService().getAllCriteria();
        HttpSession session = request.getSession();
        session.setAttribute("criterios", criterios);
    }
    
}
