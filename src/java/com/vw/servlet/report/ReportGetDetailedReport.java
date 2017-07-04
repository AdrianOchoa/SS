/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vw.servlet.report;

import com.vw.model.DetailedResultSet;
import com.vw.service.ReportingService;
import java.io.IOException;
import java.util.List;
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
@WebServlet("/ReportGetDetailedReport")
public class ReportGetDetailedReport extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        String nextPage;
        
        String criteriaID = request.getParameter("id");
        String type = request.getParameter("type");
        String initialDate = request.getParameter("initialDate");
        String finalDate = request.getParameter("finalDate");
        String solicitante = request.getParameter("applicant");
        
        List<DetailedResultSet> result 
                = new ReportingService().generateDetailedReport(criteriaID, type, initialDate, finalDate, solicitante);
        HttpSession session = request.getSession();
        session.setAttribute("result", result);
        nextPage = "report/reportDetailedReport.jsp";
        request.getRequestDispatcher(nextPage).forward(request, response);
    }
    
}
