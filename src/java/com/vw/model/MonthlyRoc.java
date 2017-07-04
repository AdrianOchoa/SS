/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vw.model;

/**
 * This class is a bean that represents the monthly roc table
 * @author Adrián Ochoa Martínez
 */
public class MonthlyRoc {
    
    private String id;
    private String criteriaID;
    private String numeroHits;
    private String daysSinceActivation;
    
    public MonthlyRoc (String id, String criteriaID, String numeroHits, String daysSinceActivation) {
        this.id = id;
        this.criteriaID = criteriaID;
        this.numeroHits = numeroHits;
        this.daysSinceActivation = daysSinceActivation;
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
     * @return the criteriaID
     */
    public String getCriteriaID() {
        return criteriaID;
    }

    /**
     * @param criteriaID the criteriaID to set
     */
    public void setCriteriaID(String criteriaID) {
        this.criteriaID = criteriaID;
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
     * @return the daysSinceActivation
     */
    public String getDaysSinceActivation() {
        return daysSinceActivation;
    }

    /**
     * @param daysSinceActivation the daysSinceActivation to set
     */
    public void setDaysSinceActivation(String daysSinceActivation) {
        this.daysSinceActivation = daysSinceActivation;
    }

}
