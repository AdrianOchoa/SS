/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vw.model;

/**
 * This class is a bean that represents the claim table
 * @author Adrián Ochoa Martínez
 */
public class Claim {
    
    private String id;
    private String chasis;
    private String numeroReclamacion;
    private String serial;
    private String manufacturer;
    private String isoLand;
    private String importer;
    private String dealer;
    private String claimDate;
    private String serviceID;
    private String currentStatus;
    private String fechaReparacion;
    private String criterioID;
    private double valorManoObraImp;
    private double valorMaterialNivelImp;
    private double valorTotalImp;
    private int utsImp;
    private double importeTotalNivelFabricaImpEuros;
    private double manoObraFabricaImportadorEuros;
    private int utsFabricaImportador;
    private double totalMaterialNivelFabricanteEuros;
    private String monedaNivelImportador;
    private String monedaNivelFabricante;
    
    /**
     * 
     */
    public Claim () {
        
    }
    
    /**
     * 
     * @param id
     * @param chasis
     * @param numeroReclamacion
     * @param serial
     * @param manufacturer
     * @param isoLand
     * @param importer
     * @param dealer
     * @param claimDate
     * @param serviceID
     * @param currentStatus
     * @param fechaReparacion
     * @param criterioID
     * @param valorManoObraimp
     * @param valorMaterialNivelImp
     * @param valorTotalImp
     * @param utsImp
     * @param importeTotalNivelFabricaImpEuros
     * @param manoObraFabricaImportadorEuros
     * @param utsFabricaImpordator
     * @param totalMaterialNivelFabricanteEuros
     * @param monedaNivelImportador
     * @param monedaNivelFabricante 
     */
    public Claim (String id, String chasis, String numeroReclamacion, String serial,
            String manufacturer, String isoLand, String importer, String dealer, String claimDate,
            String serviceID, String currentStatus, String fechaReparacion, String criterioID,
            double valorManoObraimp, double valorMaterialNivelImp, double valorTotalImp,
            int utsImp, double importeTotalNivelFabricaImpEuros, double manoObraFabricaImportadorEuros,
            int utsFabricaImpordator, double totalMaterialNivelFabricanteEuros,
            String monedaNivelImportador, String monedaNivelFabricante) {
        this.id = id;
        this.chasis = chasis;
        this.numeroReclamacion = numeroReclamacion;
        this.serial = serial;
        this.manufacturer = manufacturer;
        this.isoLand = isoLand;
        this.importer = importer;
        this.dealer = dealer;
        this.claimDate = claimDate;
        this.serviceID = serviceID;
        this.currentStatus = currentStatus;
        this.fechaReparacion = fechaReparacion;
        this.criterioID = criterioID;
        this.valorManoObraImp = valorManoObraimp;
        this.valorMaterialNivelImp = valorMaterialNivelImp;
        this.valorTotalImp = valorTotalImp;
        this.utsImp = utsImp;
        this.importeTotalNivelFabricaImpEuros = importeTotalNivelFabricaImpEuros;
        this.manoObraFabricaImportadorEuros = manoObraFabricaImportadorEuros;
        this.utsFabricaImportador = utsFabricaImpordator;
        this.totalMaterialNivelFabricanteEuros = totalMaterialNivelFabricanteEuros;
        this.monedaNivelImportador = monedaNivelImportador;
        this.monedaNivelFabricante = monedaNivelFabricante;
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
     * @return the serial
     */
    public String getSerial() {
        return serial;
    }

    /**
     * @param serial the serial to set
     */
    public void setSerial(String serial) {
        this.serial = serial;
    }

    /**
     * @return the manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * @param manufacturer the manufacturer to set
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * @return the isoLand
     */
    public String getIsoLand() {
        return isoLand;
    }

    /**
     * @param isoLand the isoLand to set
     */
    public void setIsoLand(String isoLand) {
        this.isoLand = isoLand;
    }

    /**
     * @return the importer
     */
    public String getImporter() {
        return importer;
    }

    /**
     * @param importer the importer to set
     */
    public void setImporter(String importer) {
        this.importer = importer;
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
     * @return the serviceID
     */
    public String getServiceID() {
        return serviceID;
    }

    /**
     * @param serviceID the serviceID to set
     */
    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    /**
     * @return the currentStatus
     */
    public String getCurrentStatus() {
        return currentStatus;
    }

    /**
     * @param currentStatus the currentStatus to set
     */
    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    /**
     * @return the fechaReparacion
     */
    public String getFechaReparacion() {
        return fechaReparacion;
    }

    /**
     * @param fechaReparacion the fechaReparacion to set
     */
    public void setFechaReparacion(String fechaReparacion) {
        this.fechaReparacion = fechaReparacion;
    }

    /**
     * @return the criterioID
     */
    public String getCriterioID() {
        return criterioID;
    }

    /**
     * @param criterioID the criterioID to set
     */
    public void setCriterioID(String criterioID) {
        this.criterioID = criterioID;
    }

    /**
     * @return the valorManoObraImp
     */
    public double getValorManoObraImp() {
        return valorManoObraImp;
    }

    /**
     * @param valorManoObraImp the valorManoObraImp to set
     */
    public void setValorManoObraImp(double valorManoObraImp) {
        this.valorManoObraImp = valorManoObraImp;
    }

    /**
     * @return the valorMaterialNivelImp
     */
    public double getValorMaterialNivelImp() {
        return valorMaterialNivelImp;
    }

    /**
     * @param valorMaterialNivelImp the valorMaterialNivelImp to set
     */
    public void setValorMaterialNivelImp(double valorMaterialNivelImp) {
        this.valorMaterialNivelImp = valorMaterialNivelImp;
    }

    /**
     * @return the valorTotalImp
     */
    public double getValorTotalImp() {
        return valorTotalImp;
    }

    /**
     * @param valorTotalImp the valorTotalImp to set
     */
    public void setValorTotalImp(double valorTotalImp) {
        this.valorTotalImp = valorTotalImp;
    }

    /**
     * @return the utsImp
     */
    public int getUtsImp() {
        return utsImp;
    }

    /**
     * @param utsImp the utsImp to set
     */
    public void setUtsImp(int utsImp) {
        this.utsImp = utsImp;
    }

    /**
     * @return the importeTotalNivelFabricaImpEuros
     */
    public double getImporteTotalNivelFabricaImpEuros() {
        return importeTotalNivelFabricaImpEuros;
    }

    /**
     * @param importeTotalNivelFabricaImpEuros the importeTotalNivelFabricaImpEuros to set
     */
    public void setImporteTotalNivelFabricaImpEuros(double importeTotalNivelFabricaImpEuros) {
        this.importeTotalNivelFabricaImpEuros = importeTotalNivelFabricaImpEuros;
    }

    /**
     * @return the manoObraFabricaImportadorEuros
     */
    public double getManoObraFabricaImportadorEuros() {
        return manoObraFabricaImportadorEuros;
    }

    /**
     * @param manoObraFabricaImportadorEuros the manoObraFabricaImportadorEuros to set
     */
    public void setManoObraFabricaImportadorEuros(double manoObraFabricaImportadorEuros) {
        this.manoObraFabricaImportadorEuros = manoObraFabricaImportadorEuros;
    }

    /**
     * @return the utsFabricaImportador
     */
    public int getUtsFabricaImportador() {
        return utsFabricaImportador;
    }

    /**
     * @param utsFabricaImportador the utsFabricaImportador to set
     */
    public void setUtsFabricaImportador(int utsFabricaImportador) {
        this.utsFabricaImportador = utsFabricaImportador;
    }

    /**
     * @return the totalMaterialNivelFabricanteEuros
     */
    public double getTotalMaterialNivelFabricanteEuros() {
        return totalMaterialNivelFabricanteEuros;
    }

    /**
     * @param totalMaterialNivelFabricanteEuros the totalMaterialNivelFabricanteEuros to set
     */
    public void setTotalMaterialNivelFabricanteEuros(double totalMaterialNivelFabricanteEuros) {
        this.totalMaterialNivelFabricanteEuros = totalMaterialNivelFabricanteEuros;
    }

    /**
     * @return the monedaNivelImportador
     */
    public String getMonedaNivelImportador() {
        return monedaNivelImportador;
    }

    /**
     * @param monedaNivelImportador the monedaNivelImportador to set
     */
    public void setMonedaNivelImportador(String monedaNivelImportador) {
        this.monedaNivelImportador = monedaNivelImportador;
    }

    /**
     * @return the monedaNivelFabricante
     */
    public String getMonedaNivelFabricante() {
        return monedaNivelFabricante;
    }

    /**
     * @param monedaNivelFabricante the monedaNivelFabricante to set
     */
    public void setMonedaNivelFabricante(String monedaNivelFabricante) {
        this.monedaNivelFabricante = monedaNivelFabricante;
    }

}
