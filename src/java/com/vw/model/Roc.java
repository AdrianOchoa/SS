/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vw.model;

/**
 * This class is a bean that represents the roc table
 * @author Adrián Ochoa Martínez
 */
public class Roc {
    
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
    private int status;
    private String criterios;
    private String criteriosVersion;
    private double amount;
    
    /**
     * Empty
     */
    public Roc () {
        
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
     * @param status
     * @param criterios
     * @param criteriosVersion 
     */
    public Roc (String id, String chasis, String numeroReclamacion, String claimSerial,
            String importador, String paisIso, String fabricante,
            String taller, String claimDate, String nAT, String claimTypeImp, 
            String claimTypeMft, int status, String criterios, String criteriosVersion,
            double amount) {
        this.id = id;
        this.chasis = chasis;
        this.numeroReclamacion = numeroReclamacion;
        this.claimSerial = claimSerial;
        this.importador = importador;
        this.paisIso = paisIso;
        this.fabricante = fabricante;
        this.taller = taller;
        this.claimDate = claimDate;
        this.nAT = nAT;
        this.claimTypeImp = claimTypeImp;
        this.claimTypeMft = claimTypeMft;
        this.status = status;
        this.criterios = criterios;
        this.criteriosVersion = criteriosVersion;
        this.amount = amount;
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
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the criterios
     */
    public String getCriterios() {
        return criterios;
    }

    /**
     * @param criterios the criterios to set
     */
    public void setCriterios(String criterios) {
        this.criterios = criterios;
    }

    /**
     * @return the criteriosVersion
     */
    public String getCriteriosVersion() {
        return criteriosVersion;
    }

    /**
     * @param criteriosVersion the criteriosVersion to set
     */
    public void setCriteriosVersion(String criteriosVersion) {
        this.criteriosVersion = criteriosVersion;
    }

}
