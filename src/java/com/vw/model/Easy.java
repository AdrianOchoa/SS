/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vw.model;

/**
 * This class is a bean that represents the easy table
 * @author Adrián Ochoa Martínez
 */
public class Easy {
    
    private String id;
    private String chasis;
    private String numeroReclamacion;
    private String claimSerial;
    private String importador;
    private String paisIso;
    private String fabricante;
    private String taller;
    private String claimDate;
    private String nAT;
    private String claimTypeImp;
    private String claimTypeMft;
    private int ageLevel;
    private int ageOverAll;
    private String level;
    private String status;
    private String requiredAction;
    private String dealerStatus;
    private String impCurrency;
    private double valueImpCurrency;
    
    /**
     * 
     */
    public Easy () {
        
    }
    
    /**
     * 
     * @param id
     * @param chasis
     * @param numeroReclamacion
     * @param claimSerial
     * @param claimVersion
     * @param importador
     * @param paisIso
     * @param fabricante
     * @param taller
     * @param claimDate
     * @param nAT
     * @param claimTypeImp
     * @param claimTypeMft
     * @param ageLevel
     * @param ageOverAll
     * @param level
     * @param status
     * @param requiredAction
     * @param dealerStatus
     * @param impCurrency
     * @param valueImpCurrency 
     */
    public Easy (String id, String chasis, String numeroReclamacion, String claimSerial,
            String claimVersion, String importador, String paisIso, String fabricante,
            String taller, String claimDate, String nAT, String claimTypeImp,
            String claimTypeMft, int ageLevel, int ageOverAll, String level, String status,
            String requiredAction, String dealerStatus, String impCurrency, String valueImpCurrency) {
        this.id = id;
        this.chasis = chasis;
        this.numeroReclamacion = numeroReclamacion;
        this.claimSerial = claimSerial;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the chasis
     */
    public String getChasis() {
        return chasis;
    }

    /**
     * @param chasis the chasis to set
     */
    public void setChasis(String chasis) {
        this.chasis = chasis;
    }

    /**
     * @return the numeroReclamacion
     */
    public String getNumeroReclamacion() {
        return numeroReclamacion;
    }

    /**
     * @param numeroReclamacion the numeroReclamacion to set
     */
    public void setNumeroReclamacion(String numeroReclamacion) {
        this.numeroReclamacion = numeroReclamacion;
    }

    /**
     * @return the claimSerial
     */
    public String getClaimSerial() {
        return claimSerial;
    }

    /**
     * @param claimSerial the claimSerial to set
     */
    public void setClaimSerial(String claimSerial) {
        this.claimSerial = claimSerial;
    }

    /**
     * @return the importador
     */
    public String getImportador() {
        return importador;
    }

    /**
     * @param importador the importador to set
     */
    public void setImportador(String importador) {
        this.importador = importador;
    }

    /**
     * @return the paisIso
     */
    public String getPaisIso() {
        return paisIso;
    }

    /**
     * @param paisIso the paisIso to set
     */
    public void setPaisIso(String paisIso) {
        this.paisIso = paisIso;
    }

    /**
     * @return the fabricante
     */
    public String getFabricante() {
        return fabricante;
    }

    /**
     * @param fabricante the fabricante to set
     */
    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    /**
     * @return the taller
     */
    public String getTaller() {
        return taller;
    }

    /**
     * @param taller the taller to set
     */
    public void setTaller(String taller) {
        this.taller = taller;
    }

    /**
     * @return the claimDate
     */
    public String getClaimDate() {
        return claimDate;
    }

    /**
     * @param claimDate the claimDate to set
     */
    public void setClaimDate(String claimDate) {
        this.claimDate = claimDate;
    }

    /**
     * @return the nAT
     */
    public String getnAT() {
        return nAT;
    }

    /**
     * @param nAT the nAT to set
     */
    public void setnAT(String nAT) {
        this.nAT = nAT;
    }

    /**
     * @return the claimTypeImp
     */
    public String getClaimTypeImp() {
        return claimTypeImp;
    }

    /**
     * @param claimTypeImp the claimTypeImp to set
     */
    public void setClaimTypeImp(String claimTypeImp) {
        this.claimTypeImp = claimTypeImp;
    }

    /**
     * @return the claimTypeMft
     */
    public String getClaimTypeMft() {
        return claimTypeMft;
    }

    /**
     * @param claimTypeMft the claimTypeMft to set
     */
    public void setClaimTypeMft(String claimTypeMft) {
        this.claimTypeMft = claimTypeMft;
    }

    /**
     * @return the ageLevel
     */
    public int getAgeLevel() {
        return ageLevel;
    }

    /**
     * @param ageLevel the ageLevel to set
     */
    public void setAgeLevel(int ageLevel) {
        this.ageLevel = ageLevel;
    }

    /**
     * @return the ageOverAll
     */
    public int getAgeOverAll() {
        return ageOverAll;
    }

    /**
     * @param ageOverAll the ageOverAll to set
     */
    public void setAgeOverAll(int ageOverAll) {
        this.ageOverAll = ageOverAll;
    }

    /**
     * @return the level
     */
    public String getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the requiredAction
     */
    public String getRequiredAction() {
        return requiredAction;
    }

    /**
     * @param requiredAction the requiredAction to set
     */
    public void setRequiredAction(String requiredAction) {
        this.requiredAction = requiredAction;
    }

    /**
     * @return the dealerStatus
     */
    public String getDealerStatus() {
        return dealerStatus;
    }

    /**
     * @param dealerStatus the dealerStatus to set
     */
    public void setDealerStatus(String dealerStatus) {
        this.dealerStatus = dealerStatus;
    }

    /**
     * @return the impCurrency
     */
    public String getImpCurrency() {
        return impCurrency;
    }

    /**
     * @param impCurrency the impCurrency to set
     */
    public void setImpCurrency(String impCurrency) {
        this.impCurrency = impCurrency;
    }

    /**
     * @return the valueImpCurrency
     */
    public double getValueImpCurrency() {
        return valueImpCurrency;
    }

    /**
     * @param valueImpCurrency the valueImpCurrency to set
     */
    public void setValueImpCurrency(double valueImpCurrency) {
        this.valueImpCurrency = valueImpCurrency;
    }

}
