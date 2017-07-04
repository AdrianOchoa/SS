/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vw.model;

/**
 * This class is a bean that represents the data warehouse table
 * @author Adrián Ochoa Martínez
 */
public class DataWareHouse {
    
    private String id;
    private String chasis;
    private String numeroReclamacion;
    private String claimSerial;
    private String marca;
    private String dealer;
    private String fechaReclamacion;
    private String fechaPago;
    private String platform;
    private String nAT;
    private String damageCode;
    private String descripcion;
    private String sparePartDescription;
    private String claimAdjuster;
    private int uts;
    private double moInt;
    private double matIntSinProfit;
    private double moExt;
    private double matExtSinProfit;
    private double totalSinProfit;
    private double totalPagado;
    
    /**
     * 
     */
    public DataWareHouse () {
        
    }
    
    /**
     * 
     * @param id
     * @param chasis
     * @param numeroReclamacion
     * @param claimSerial
     * @param marca
     * @param dealer
     * @param fechaReclamacion
     * @param fechaPago
     * @param platform
     * @param nAT
     * @param damageCode
     * @param descripcion
     * @param sparePartDescription
     * @param claimAdjuster
     * @param uts
     * @param moInt
     * @param matIntSinProfit
     * @param moExt
     * @param matExtSinProfit
     * @param totalSinProfit
     * @param totalPagado 
     */
    public DataWareHouse (String id, String chasis, String numeroReclamacion, String claimSerial,
            String marca, String dealer, String fechaReclamacion, String fechaPago, String platform,
            String nAT, String damageCode, String descripcion, String sparePartDescription,
            String claimAdjuster, int uts, double moInt, double matIntSinProfit, 
            double moExt, double matExtSinProfit, double totalSinProfit, double totalPagado) {
        this.id = id;
        this.chasis = chasis;
        this.numeroReclamacion = numeroReclamacion;
        this.claimSerial = claimSerial;
        this.marca = marca;
        this.dealer = dealer;
        this.fechaReclamacion = fechaReclamacion;
        this.fechaPago = fechaPago;
        this.platform = platform;
        this.nAT = nAT;
        this.damageCode = damageCode;
        this.descripcion = descripcion;
        this.sparePartDescription = sparePartDescription;
        this.claimAdjuster = claimAdjuster;
        this.uts = uts;
        this.moInt = moInt;
        this.matIntSinProfit = matIntSinProfit;
        this.moExt = moExt;
        this.matExtSinProfit = matExtSinProfit;
        this.totalSinProfit = totalSinProfit;
        this.totalPagado = totalPagado;
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

    /**
     * @return the fechaReclamacion
     */
    public String getFechaReclamacion() {
        return fechaReclamacion;
    }

    /**
     * @param fechaReclamacion the fechaReclamacion to set
     */
    public void setFechaReclamacion(String fechaReclamacion) {
        this.fechaReclamacion = fechaReclamacion;
    }

    /**
     * @return the fechaPago
     */
    public String getFechaPago() {
        return fechaPago;
    }

    /**
     * @param fechaPago the fechaPago to set
     */
    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    /**
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * @param platform the platform to set
     */
    public void setPlatform(String platform) {
        this.platform = platform;
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
     * @return the damageCode
     */
    public String getDamageCode() {
        return damageCode;
    }

    /**
     * @param damageCode the damageCode to set
     */
    public void setDamageCode(String damageCode) {
        this.damageCode = damageCode;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the sparePartDescription
     */
    public String getSparePartDescription() {
        return sparePartDescription;
    }

    /**
     * @param sparePartDescription the sparePartDescription to set
     */
    public void setSparePartDescription(String sparePartDescription) {
        this.sparePartDescription = sparePartDescription;
    }

    /**
     * @return the claimAdjuster
     */
    public String getClaimAdjuster() {
        return claimAdjuster;
    }

    /**
     * @param claimAdjuster the claimAdjuster to set
     */
    public void setClaimAdjuster(String claimAdjuster) {
        this.claimAdjuster = claimAdjuster;
    }

    /**
     * @return the uts
     */
    public int getUts() {
        return uts;
    }

    /**
     * @param uts the uts to set
     */
    public void setUts(int uts) {
        this.uts = uts;
    }

    /**
     * @return the moInt
     */
    public double getMoInt() {
        return moInt;
    }

    /**
     * @param moInt the moInt to set
     */
    public void setMoInt(double moInt) {
        this.moInt = moInt;
    }

    /**
     * @return the matIntSinProfit
     */
    public double getMatIntSinProfit() {
        return matIntSinProfit;
    }

    /**
     * @param matIntSinProfit the matIntSinProfit to set
     */
    public void setMatIntSinProfit(double matIntSinProfit) {
        this.matIntSinProfit = matIntSinProfit;
    }

    /**
     * @return the moExt
     */
    public double getMoExt() {
        return moExt;
    }

    /**
     * @param moExt the moExt to set
     */
    public void setMoExt(double moExt) {
        this.moExt = moExt;
    }

    /**
     * @return the matExtSinProfit
     */
    public double getMatExtSinProfit() {
        return matExtSinProfit;
    }

    /**
     * @param matExtSinProfit the matExtSinProfit to set
     */
    public void setMatExtSinProfit(double matExtSinProfit) {
        this.matExtSinProfit = matExtSinProfit;
    }

    /**
     * @return the totalSinProfit
     */
    public double getTotalSinProfit() {
        return totalSinProfit;
    }

    /**
     * @param totalSinProfit the totalSinProfit to set
     */
    public void setTotalSinProfit(double totalSinProfit) {
        this.totalSinProfit = totalSinProfit;
    }

    /**
     * @return the totalPagado
     */
    public double getTotalPagado() {
        return totalPagado;
    }

    /**
     * @param totalPagado the totalPagado to set
     */
    public void setTotalPagado(double totalPagado) {
        this.totalPagado = totalPagado;
    }

}
