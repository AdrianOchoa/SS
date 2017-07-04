/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vw.servlet.report;

import com.vw.model.ResultSet;
import com.vw.service.ReportingService;
import java.io.IOException;
import java.util.HashMap;
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
@WebServlet("/ReportCanceledClaimsByCriteriaServlet")
public class ReportCanceledClaimsByCriteriaServlet extends HttpServlet {
    
    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        String nextPage;
        String month = getMonth(request.getParameter("mes").toLowerCase());
        String year = request.getParameter("anio");
        String days = getDays(month);
        String initialDate = year + month + "01";
        String finalDate = year + month + days;
        List<ResultSet> result = 
                new ReportingService().generateCancelledClaimsByCriteriaReport(initialDate, finalDate);
        HttpSession session = request.getSession();
        session.setAttribute("result", result);
        session.setAttribute("initialDate", initialDate);
        session.setAttribute("finalDate", finalDate);
        nextPage = "report/reportCanceledClaimsByCriteria.jsp";
        request.getRequestDispatcher(nextPage).forward(request, response);
    }
    
    private String getMonth (String month) {
        HashMap<String, String> map = new HashMap();
        map.put("enero", "01");
        map.put("febrero", "02");
        map.put("marzo", "03");
        map.put("abril", "04");
        map.put("mayo", "05");
        map.put("junio", "06");
        map.put("julio", "07");
        map.put("agosto", "08");
        map.put("septiembre", "09");
        map.put("octubre", "10");
        map.put("noviembre", "11");
        map.put("diciembre", "12");
        return map.get(month);
    }
    
    private String getDays (String month) {
        HashMap<String, String> map = new HashMap();
        map.put("01", "31");
        map.put("02", "28");
        map.put("03", "31");
        map.put("04", "30");
        map.put("05", "31");
        map.put("06", "30");
        map.put("07", "31");
        map.put("08", "31");
        map.put("09", "30");
        map.put("10", "31");
        map.put("11", "30");
        map.put("12", "31");
        return map.get(month);
    }

}
