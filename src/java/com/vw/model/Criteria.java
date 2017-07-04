/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vw.model;

/**
 * This class is a bean that represents the Criteria
 * @author Adrián Ochoa Martínez
 */
public class Criteria {

    private String criterioID;
    private String idNuevo;
    private String idViejo;
    private String estatus;
    private String departamento;
    private String tipo;
    private String nivel;
    private String objetivo;
    private String grupo;
    private String contenido;
    private String comentario;
    private String datosDetener;
    private String averia;
    private String danio;
    private String marca;
    private String claveComercial;
    private String modelo;
    private String garantiaAfecta;
    private String solicitante;
    private String fechaCreacion;
    private String fechaRevision;
    private String periodoRevision;
    private String agregadoPor;
    private char aprobado;

    /**
     * 
     */
    public Criteria() {
        
    }
    
    /**
     * 
     * @param criterioID
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
     * @param datosDetener
     * @param averia
     * @param danio
     * @param marca
     * @param claveComercial
     * @param modelo
     * @param garantiaAfecta
     * @param solicitante
     * @param fechaCreacion
     * @param fechaRevision
     * @param periodoRevision
     * @param agregadoPor
     * @param aprobado 
     */
    public Criteria (String criterioID, String idNuevo, String idViejo, String estatus,
            String departamento, String tipo, String nivel, String objetivo, String grupo,
            String contenido, String comentario, String datosDetener, String averia, 
            String danio, String marca, String claveComercial, String modelo, String garantiaAfecta,
            String solicitante, String fechaCreacion, String fechaRevision, String periodoRevision,
            String agregadoPor, char aprobado) {
        this.criterioID = criterioID;
        this.idNuevo = idNuevo;
        this.idViejo = idViejo;
        this.estatus = estatus;
        this.departamento = departamento;
        this.tipo = tipo;
        this.nivel = nivel;
        this.objetivo = objetivo;
        this.grupo = grupo;
        this.contenido = contenido;
        this.comentario = comentario;
        this.datosDetener = datosDetener;
        this.averia = averia;
        this.danio = danio;
        this.marca = marca;
        this.claveComercial = claveComercial;
        this.modelo = modelo;
        this.garantiaAfecta = garantiaAfecta;
        this.solicitante = solicitante;
        this.fechaCreacion = fechaCreacion;
        this.fechaRevision = fechaRevision;
        this.periodoRevision = periodoRevision;
        this.agregadoPor = agregadoPor;
        this.aprobado = aprobado;
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
     * @return the idNuevo
     */
    public String getIdNuevo() {
        return idNuevo;
    }

    /**
     * @param idNuevo the idNuevo to set
     */
    public void setIdNuevo(String idNuevo) {
        this.idNuevo = idNuevo;
    }

    /**
     * @return the idViejo
     */
    public String getIdViejo() {
        return idViejo;
    }

    /**
     * @param idViejo the idViejo to set
     */
    public void setIdViejo(String idViejo) {
        this.idViejo = idViejo;
    }

    /**
     * @return the estatus
     */
    public String getEstatus() {
        return estatus;
    }

    /**
     * @param estatus the estatus to set
     */
    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    /**
     * @return the departamento
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * @param departamento the departamento to set
     */
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the nivel
     */
    public String getNivel() {
        return nivel;
    }

    /**
     * @param nivel the nivel to set
     */
    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    /**
     * @return the objetivo
     */
    public String getObjetivo() {
        return objetivo;
    }

    /**
     * @param objetivo the objetivo to set
     */
    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    /**
     * @return the grupo
     */
    public String getGrupo() {
        return grupo;
    }

    /**
     * @param grupo the grupo to set
     */
    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    /**
     * @return the contenido
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * @param contenido the contenido to set
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    /**
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param comentario the comentario to set
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * @return the datosDetener
     */
    public String getDatosDetener() {
        return datosDetener;
    }

    /**
     * @param datosDetener the datosDetener to set
     */
    public void setDatosDetener(String datosDetener) {
        this.datosDetener = datosDetener;
    }

    /**
     * @return the averia
     */
    public String getAveria() {
        return averia;
    }

    /**
     * @param averia the averia to set
     */
    public void setAveria(String averia) {
        this.averia = averia;
    }

    /**
     * @return the danio
     */
    public String getDanio() {
        return danio;
    }

    /**
     * @param danio the danio to set
     */
    public void setDanio(String danio) {
        this.danio = danio;
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
     * @return the claveComercial
     */
    public String getClaveComercial() {
        return claveComercial;
    }

    /**
     * @param claveComercial the claveComercial to set
     */
    public void setClaveComercial(String claveComercial) {
        this.claveComercial = claveComercial;
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the garantiaAfecta
     */
    public String getGarantiaAfecta() {
        return garantiaAfecta;
    }

    /**
     * @param garantiaAfecta the garantiaAfecta to set
     */
    public void setGarantiaAfecta(String garantiaAfecta) {
        this.garantiaAfecta = garantiaAfecta;
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
     * @return the fechaCreacion
     */
    public String getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @param fechaCreacion the fechaCreacion to set
     */
    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * @return the fechaRevision
     */
    public String getFechaRevision() {
        return fechaRevision;
    }

    /**
     * @param fechaRevision the fechaRevision to set
     */
    public void setFechaRevision(String fechaRevision) {
        this.fechaRevision = fechaRevision;
    }

    /**
     * @return the periodoRevision
     */
    public String getPeriodoRevision() {
        return periodoRevision;
    }

    /**
     * @param periodoRevision the periodoRevision to set
     */
    public void setPeriodoRevision(String periodoRevision) {
        this.periodoRevision = periodoRevision;
    }

    /**
     * @return the agregadoPor
     */
    public String getAgregadoPor() {
        return agregadoPor;
    }

    /**
     * @param agregadoPor the agregadoPor to set
     */
    public void setAgregadoPor(String agregadoPor) {
        this.agregadoPor = agregadoPor;
    }

    /**
     * @return the aprobado
     */
    public char getAprobado() {
        return aprobado;
    }

    /**
     * @param aprobado the aprobado to set
     */
    public void setAprobado(char aprobado) {
        this.aprobado = aprobado;
    }
}
