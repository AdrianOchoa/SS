/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vw.model;

/**
 *
 * @author Adrián Ochoa Martínez
 */
public class MissingCriteria implements Comparable<MissingCriteria> {

    private String criteriaID;
    private String criteriaMessage;

    public MissingCriteria(String criteriaID, String criteriaMessage) {
        this.criteriaID = criteriaID;
        this.criteriaMessage = criteriaMessage;
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
     * @return the criteriaMessage
     */
    public String getCriteriaMessage() {
        return criteriaMessage;
    }

    /**
     * @param criteriaMessage the criteriaMessage to set
     */
    public void setCriteriaMessage(String criteriaMessage) {
        this.criteriaMessage = criteriaMessage;
    }

    @Override
    public int compareTo(MissingCriteria o) {
        if (this.criteriaMessage.compareTo(o.criteriaID) == 0) {
            return this.criteriaID.compareTo(o.criteriaID);
        } else {
            return this.criteriaMessage.compareTo(o.criteriaMessage);
        }
    }
}
