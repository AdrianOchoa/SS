/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vw.model;

/**
 * This class is a data container that will be filled with the data for the
 * specific report Total Claims by Brand
 *
 * @author Adrián Ochoa Martínez
 */
public class ResultSetForTotalClaims implements Comparable<ResultSetForTotalClaims> {

    private String totalClaims;
    private String brand;

    public ResultSetForTotalClaims() {
    }

    public ResultSetForTotalClaims(String totalClaims, String brand) {
        this.totalClaims = totalClaims;
        this.brand = brand;
    }

    /**
     * @return the totalClaims
     */
    public String getTotalClaims() {
        return totalClaims;
    }

    /**
     * @param totalClaims the totalClaims to set
     */
    public void setTotalClaims(String totalClaims) {
        this.totalClaims = totalClaims;
    }

    /**
     * @return the brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @param brand the brand to set
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public int compareTo(ResultSetForTotalClaims o) {
        int i = Integer.parseInt(o.totalClaims);
        int n = Integer.parseInt(this.totalClaims);
        return i - n;
    }
}
