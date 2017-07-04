/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
 * @author Adrián
 */
@WebServlet("/CriteriaSendInfoBussinessApprovedServlet")
public class CriteriaSendInfoBussinessApprovedServlet extends HttpServlet {
    
    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String id = request.getParameter("id");
        HttpSession session = request.getSession();
        Criteria criterioForEdit = new CriteriaService().getCriteriaForEdit(id);
        session.setAttribute("criterio", criterioForEdit);
        session.setAttribute("id", id);
        request.getRequestDispatcher("criteria/criteriaSeeInfoToBeApprovedByBussiness.jsp").forward(request, response);
    }
    
}
