/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
 *
 * @author Adrián
 */
@WebServlet("/CriteriaGetNotBussinessApprovedCriteriaServlet")
public class CriteriaGetNotBussinessApprovedCriteriaServlet extends HttpServlet {
    
    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<String> criterios = new CriteriaService().getNotBussinessApprovedCriteriaById();
        request.getSession().setAttribute("criterios", criterios);
    }
    
}
