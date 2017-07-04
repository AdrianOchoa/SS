/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vw.service;

import com.vw.model.DetailedResultSet;
import com.vw.model.ResultSet;
import com.vw.model.ResultSetForTotalClaims;
import com.vw.util.DataBaseHelper;
import java.util.List;

/**
 * This class is a comunication media for the servlets that manages the reporting processes
 * and the data base handler methods
 * @author Adrián Ochoa Martínez
 */
public class ReportingService {
    
    private final DataBaseHelper dataBaseHelper;
    
    /**
     * 
     */
    public ReportingService () {
        dataBaseHelper = new DataBaseHelper();
    }
    
    public List<DetailedResultSet> generateDetailedReport (String id, String type,
            String initialDate, String finalDate, String solicitante) {
        dataBaseHelper.getConnection();
        List<DetailedResultSet> result = dataBaseHelper.getDetailedReport(id, type, initialDate, finalDate, solicitante);
        dataBaseHelper.closeConnection();
        return result;
    }
    
    /**
     * This method will process the necessary info to fill the adjust by analist
     * report.
     * It's used in the reportAdjustByAnalist jsp/ReportAdjustByAnalistServlet
     * @param initialDate
     * @param finalDate
     * @return a list of ResultSet which contains the detailed info about regular criteria
     */
    public List<ResultSet> generateAdjustByAnalistReport(String initialDate, String finalDate) {
        dataBaseHelper.getConnection();
        List<ResultSet> result = dataBaseHelper.getAdjustByAnalist(initialDate, finalDate);
        dataBaseHelper.closeConnection();
        return result;
    }
    
    /**
     * This method will process the necessary info to fill the adjust by intelligent
     * criteria report.
     * It's used in the reportAdjustByCriteria jsp/ReportAdjustByCriteriaServlet
     * @param initialDate
     * @param finalDate
     * @return a list of ResultSet which contains the detailed info about intelligent criteria
     */
    public List<ResultSet> generateAdjustByCriteriaReport (String initialDate, String finalDate) {
        dataBaseHelper.getConnection();
        List<ResultSet> result = dataBaseHelper.getAdjustByCriteria(initialDate, finalDate);
        dataBaseHelper.closeConnection();
        return result;
    }
    
    /**
     * This method will process the necessary info to fill the canceled claims
     * by analis report
     * It's used int the reportCanceledClaimsByAnalist jsp/ReportCanceledClaimsByAnalistServlet
     * @param initialDate
     * @param finalDate
     * @return a list 
     */
    public List<ResultSet> generateCancelledClaimsByAnalistReport (String initialDate, String finalDate) {
        dataBaseHelper.getConnection();
        List<ResultSet> result = dataBaseHelper.getCanceledClaimsByAnalist(initialDate, finalDate);
        dataBaseHelper.closeConnection();
        return result;
    }
    
    /**
     * 
     * @param initialDate
     * @param finalDate
     * @return 
     */
    public List<ResultSet> generateCancelledClaimsByCriteriaReport (String initialDate, String finalDate) {
        dataBaseHelper.getConnection();
        List<ResultSet> result = dataBaseHelper.getCanceledClaimsByCriteria(initialDate, finalDate);
        dataBaseHelper.closeConnection();
        return result;
    }
    
    /**
     * 
     * @param initialDate
     * @param finalDate
     * @return 
     */
    public List<ResultSetForTotalClaims> generateTotalClaimsByBrandReport(String initialDate, 
            String finalDate) {
        dataBaseHelper.getConnection();
        List<ResultSetForTotalClaims> result = 
                dataBaseHelper.getTotalClaimsByBrand(initialDate, finalDate);
        dataBaseHelper.closeConnection();
        return result;
    }

}
