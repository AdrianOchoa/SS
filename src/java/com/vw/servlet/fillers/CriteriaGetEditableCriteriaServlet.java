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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Adrián Ochoa Martínez
 */
@WebServlet("/CriteriaGetEditableCriteriaServlet")
public class CriteriaGetEditableCriteriaServlet extends HttpServlet {

    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        CriteriaService criteriaService = new CriteriaService();
        List<String> criterios = criteriaService.getCriteriaById();
        HttpSession session = request.getSession();
        session.setAttribute("criterios", criterios);
    }
    
}
