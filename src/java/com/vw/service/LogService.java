/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vw.service;

import com.vw.model.Usuario;
import com.vw.util.DataBaseHelper;

/**
 * This class is a comunication media for the servlets that manages the log processes
 * and the data base handler methods
 *
 * @author Adrián Ochoa Martínez
 */
public class LogService {
    
    private Usuario user;

    /**
     * this method will check if the user exists in the system and if exists,
     * that the data introduced by the user is correct
     * It's used in the LoginServlet
     *
     * @param userName
     * @param password
     * @return true if the user exists and the data is correct, false otherwise
     */
    public boolean authenticateUser(String userName, String password) {
        user = getUserByUserName(userName, password);
        if (user != null && user.getUserName().equals(userName) && user.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * this method checks that the password is correct for an username
     * @param userName
     * @param password
     * @return an User 
     */
    public Usuario getUserByUserName(String userName, String password) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper();
        try {
            dataBaseHelper.getConnection();
            user = dataBaseHelper.userExists(userName, password);
            dataBaseHelper.closeConnection();
            return user;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }
    
    /**
     * 
     * @return the logged user rol in the system (admin/regular user)
     */
    public String getUserRol () {
        return user.getRol();
    }
    
    /**
     * 
     * @return the id of the logged user
     */
    public String getUserID () {
        return user.getUserName();
    }
    
}
