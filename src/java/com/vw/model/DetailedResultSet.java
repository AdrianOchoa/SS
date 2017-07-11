/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vw.model;

/**
 * This class is a data container that will be filled with the data for the
 * specific claims affected by a criteria.
 *
 * @author Adrián Ochoa Martínez
 */
public class DetailedResultSet implements Comparable<DetailedResultSet> {

    private String id;
    private String monto;
    private String solicitante;
    private String chasis;
    private String criteriaID;
    private String claimSerial;
    private String dealer;
    private String file;

    public DetailedResultSet(String id, String monto, String solicitante,
            String chasis, String criteriaID, String claimSerial, String dealer, String file) {
        this.id = id;
        this.monto = monto;
        this.solicitante = solicitante;
        this.chasis = chasis;
        this.criteriaID = criteriaID;
        this.claimSerial = claimSerial;
        this.dealer = dealer;
        this.file = file;
    }

    public DetailedResultSet() {
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
     * @return the monto
     */
    public String getMonto() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
    public void setMonto(String monto) {
        this.monto = monto;
    }

    /**
     * @return the solicitante
     */
    public String getSolicitante() {
        return solicitante;
    }

    /**
     * @param solicitante the solicitante to set
     */
    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
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
     * @return the claimID
     */
    public String getCriteriaID() {
        return criteriaID;
    }

    /**
     * @param criteriaID the claimID to set
     */
    public void setCriteriaID(String criteriaID) {
        this.criteriaID = criteriaID;
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
     * @return the dealer
     */
    public String getDealer() {
        return dealer;
    }

    /**
     * @param dealer the dealer to set
     */
    public void setDealer(String dealer) {
        this.dealer = dealer;
    }

    @Override
    public int compareTo(DetailedResultSet o) {
        if (Double.parseDouble(o.getMonto().replace("$", "").replace(",", "")) == Double.parseDouble(this.getMonto().replace("$", "").replace(",", ""))) {
            return this.getId().compareTo(o.getId());
        } else {
            return (int) (Double.parseDouble(o.getMonto().replace("$", "").replace(",", "")) - Double.parseDouble(this.getMonto().replace("$", "").replace(",", "")));
        }
    }

    /**
     * @return the file
     */
    public String getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(String file) {
        this.file = file;
    }
}
