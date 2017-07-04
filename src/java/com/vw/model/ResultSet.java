/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vw.model;

/**
 * This class is a data container that will be filled with the data for the 
 * general reports
 * @author Adrián Ochoa Martínez
 */
public class ResultSet implements Comparable<ResultSet> {
    
    private String id;
    private String tipoCriterio;
    private String numeroHits;
    private String monto;
    private String solicitante;
    private String marca;
    private String diasActivacion;
    
    public ResultSet () {
        
    }
    
    public ResultSet (String id, String tipoCriterio, String numeroHits, String monto,
            String solicitante, String marca, String diasActivacion) {
        this.id = id;
        this.tipoCriterio = tipoCriterio;
        this.numeroHits = numeroHits;
        this.monto = monto;
        this.solicitante = solicitante;
        this.marca = marca;
        this.diasActivacion = diasActivacion;
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
     * @return the tipoCriterio
     */
    public String getTipoCriterio() {
        return tipoCriterio;
    }

    /**
     * @param tipoCriterio the tipoCriterio to set
     */
    public void setTipoCriterio(String tipoCriterio) {
        this.tipoCriterio = tipoCriterio;
    }

    /**
     * @return the numeroHits
     */
    public String getNumeroHits() {
        return numeroHits;
    }

    /**
     * @param numeroHits the numeroHits to set
     */
    public void setNumeroHits(String numeroHits) {
        this.numeroHits = numeroHits;
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
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * @return the diasActivacion
     */
    public String getDiasActivacion() {
        return diasActivacion;
    }

    /**
     * @param diasActivacion the diasActivacion to set
     */
    public void setDiasActivacion(String diasActivacion) {
        this.diasActivacion = diasActivacion;
    }

    @Override
    public int compareTo(ResultSet o) {
        return this.id.compareTo(o.id);
    }

}
