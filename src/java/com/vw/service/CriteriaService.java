/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vw.service;

import com.vw.model.Criteria;
import com.vw.model.MissingCriteria;
import com.vw.util.DataBaseHelper;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is a comunication media for the servlets that manages the criteria 
 * processes and the data base handler methods
 * @author Adrián Ochoa Martínez
 */
public class CriteriaService {

    /**
     * This method will get the data and send the request to the data base to 
     * create the solicitude for the new criteria.
     * It's used in the criteriaAdd jsp/CriteriaAddServlet.
     * @param idGenerado
     * @param idNuevo
     * @param idViejo
     * @param estatus
     * @param departamento
     * @param tipo
     * @param nivel
     * @param objetivo
     * @param grupo
     * @param contenido
     * @param comentario
     * @param datos
     * @param averia
     * @param danio
     * @param marca
     * @param claveComercial
     * @param modelo
     * @param tiposGarantia
     * @param solicitante
     * @param fechaCreacion
     * @param fechaRevision
     * @param periodo
     * @param agregadoPor
     * @return true if the solicitude was successfully created, false otherwise.
     */
    public boolean addCriteria(String idGenerado, String idNuevo, String idViejo,
            String estatus, String departamento, String tipo, String nivel, String objetivo,
            String grupo, String contenido, String comentario, String datos, String averia,
            String danio, String marca, String claveComercial, String modelo, String tiposGarantia,
            String solicitante, String fechaCreacion, String fechaRevision, int periodo, String agregadoPor) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper();
        dataBaseHelper.getConnection();
        if (dataBaseHelper.addCriteria(idGenerado, idNuevo, idViejo, estatus, departamento, tipo, nivel, objetivo, grupo, contenido, comentario, datos, averia, danio, marca, claveComercial, modelo, tiposGarantia, solicitante, fechaCreacion, fechaRevision, periodo, agregadoPor)) {
            dataBaseHelper.closeConnection();
            return true;
        }
        return false;
    }
    
    /**
     * This method will update the data for an especific criteria.
     * It's used in the criteriaEditForm jsp/CriteriaEditServlet.
     * @param ID
     * @param estatus
     * @param departamento
     * @param tipo
     * @param nivel
     * @param objetivo
     * @param grupo
     * @param contenido
     * @param comentario
     * @param datos
     * @param averia
     * @param danio
     * @param marca
     * @param claveComercial
     * @param modelo
     * @param garantiaAfecta
     * @param fechaRevision
     * @return true if the data was successfully updated, false otherwise.
     */
    public boolean editCriteria (String ID, String estatus, String departamento, 
            String tipo, String nivel, String objetivo, String grupo, String contenido,
            String comentario, String datos, String averia, String danio, String marca, 
            String claveComercial, String modelo, String garantiaAfecta,  String fechaRevision) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper();
        dataBaseHelper.getConnection();
        if(dataBaseHelper.editCriteria(ID, estatus, departamento, tipo, nivel, 
                objetivo, grupo, contenido, comentario, datos, averia, danio, marca, 
                claveComercial, modelo, garantiaAfecta, fechaRevision)) {
            dataBaseHelper.closeConnection();
            return true;
        }
        return false;
    }
    
    /**
     * This method will deactivate an especific criteria.
     * It's used in the criteriaDelete jsp/CriteriaDeleteServlet.
     * @param id
     * @return true if the criteria was seccessfully deactivated, false otherwise.
     */
    public boolean deactivateCriteria (String id) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper();
        dataBaseHelper.getConnection();
        if(dataBaseHelper.deactivateCriteriaByID(id)) {
            dataBaseHelper.closeConnection();
            return true;
        }
        return false;
    }
    
    /**
     * This method will return a list with all the info from all the criteria
     * It's used in the criteriaList jsp/CriteriaGetCriteriaServlet.
     * @return a list with all the data of all the criteria.
     */
    public List<Criteria> getAllCriteria () {
        DataBaseHelper dataBaseHelper = new DataBaseHelper();
        dataBaseHelper.getConnection();
        List<Criteria> criterios = dataBaseHelper.getAllFromCriteria();
        dataBaseHelper.closeConnection();
        return criterios;
    }
    
    /**
     * It's used in the criteriaEdit jsp/CriteriaSendInfoEditServlet.
     * @param ID
     * @return a list with the editable criteria info for an especific criteria ID
     */
    public Criteria getCriteriaForEdit (String ID) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper();
        dataBaseHelper.getConnection();
        Criteria criterios = dataBaseHelper.getEditableInfoByCriteriaID(ID);
        dataBaseHelper.closeConnection();
        return criterios;
    }
    
    /**
     * It's used in the criteriaToBeApproved jsp/CriteriaGetNotApprovedCriteriaServlet
     * @return a list with the ID of the criteria that hasn't been approved.
     */
    public List<String> getNotAdminApprovedCriteriaById () {
        DataBaseHelper dataBaseHelper = new DataBaseHelper();
        dataBaseHelper.getConnection();
        List<String> criterios = dataBaseHelper.getNotAdminApprovedCriteria();
        dataBaseHelper.closeConnection();
        return criterios;
    }
    
    public List<String> getNotBussinessApprovedCriteriaById() {
        DataBaseHelper dataBaseHelper = new DataBaseHelper();
        dataBaseHelper.getConnection();
        List<String> criterios = dataBaseHelper.getNotBussinessApprovedCriteria();
        dataBaseHelper.closeConnection();
        return criterios;
    }
    
    /**
     *  It's used in the criteriaDelete jsp/CriteriaGetCriteriaByIDsServlet
     * @return a list with the ID of the criteria that is both approved and active.
     */
    public List<String> getActiveCriteriaForId () {
        DataBaseHelper dataBaseHelper = new DataBaseHelper();
        dataBaseHelper.getConnection();
        List<String> criterios = dataBaseHelper.getActiveCriteriaByID();
        dataBaseHelper.closeConnection();
        return criterios;
    }
    
    /**
     *  It's used in the criteriaEdit jsp/CriteriaGetEditableCriteriaServlet
     * @return a list with the ID of all the criteria.
     */
    public List<String> getCriteriaById () {
        DataBaseHelper dataBaseHelper = new DataBaseHelper();
        dataBaseHelper.getConnection();
        List<String> criterios = dataBaseHelper.getAllCriteriaByID();
        dataBaseHelper.closeConnection();
        return criterios;
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    public boolean setBussinessApprovedCriteria (String id) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper();
        dataBaseHelper.getConnection();
        if(dataBaseHelper.setBussinessApprovedCriteria(id)) {
            dataBaseHelper.closeConnection();
            return true;
        } else {
            dataBaseHelper.closeConnection();
            return false;
        }
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    public boolean setAdminApprovedCriteria (String id) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper();
        dataBaseHelper.getConnection();
        if(dataBaseHelper.setAdminApprovedCriteria(id)) {
            dataBaseHelper.closeConnection();
            return true;
        } else {
            dataBaseHelper.closeConnection();
            return false;
        }
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    public boolean deleteCriteriaByID (String id) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper();
        dataBaseHelper.getConnection();
        if(dataBaseHelper.deleteCriteriaByID(id)) {
            dataBaseHelper.closeConnection();
            return true;
        } else {
            dataBaseHelper.closeConnection();
            return false;
        }
    }
    
    /**
     * This method will deactivate an especific criteria.
     * It's used in the criteriaDelete jsp/CriteriaDeleteServlet.
     * @param id
     * @return true if the criteria was seccessfully deactivated, false otherwise.
     */
    public boolean deleteCriteria (String id) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper();
        dataBaseHelper.getConnection();
        if(dataBaseHelper.deactivateCriteriaByID(id)) {
            dataBaseHelper.closeConnection();
            return true;
        }
        return false;
    }
    
    /**
     * 
     * @param month
     * @param year
     * @return 
     */
    public List<MissingCriteria> getCriteriaDifference (String month, String year) {
        List<MissingCriteria> list = new ArrayList<MissingCriteria>();
        DataBaseHelper dataBaseHelper = new DataBaseHelper();
        dataBaseHelper.getConnection();
        list = dataBaseHelper.getCriteriaDifferencess(month, year);
        dataBaseHelper.closeConnection();
        return list;
    }
    
}
