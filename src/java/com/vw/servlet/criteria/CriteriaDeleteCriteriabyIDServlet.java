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
@WebServlet("/CriteriaDeleteCriteriabyIDServlet")
public class CriteriaDeleteCriteriabyIDServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String id = request.getParameter("id");
        String nextPage = "";
        HttpSession session = request.getSession();
        if(new CriteriaService().deleteCriteriaByID(id)) {
            nextPage = "home.jsp";
            session.setAttribute("message", "El criterio se eliminó correctamente");
        } else {
            nextPage = "error.jsp";
            session.setAttribute("errorMessage", "No se pudo eliminar el criterio");
        }
        request.getRequestDispatcher(nextPage).forward(request, response);
    }
    
}
