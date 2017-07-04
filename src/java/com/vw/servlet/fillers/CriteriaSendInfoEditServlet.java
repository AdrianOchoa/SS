/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vw.servlet.fillers;

import com.vw.model.Criteria;
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
@WebServlet("/CriteriaSendInfoEditServlet")
public class CriteriaSendInfoEditServlet extends HttpServlet {

    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        String id = request.getParameter("id");
        HttpSession session = request.getSession();
        Criteria criterioForEdit = new CriteriaService().getCriteriaForEdit(id);
        session.setAttribute("criterio", criterioForEdit);
        request.getRequestDispatcher("criteria/criteriaEditForm.jsp").forward(request, response);
    }
    
}
