/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vw.service;

import com.vw.util.DataBaseHelper;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is a service that will manage all the methods related to users.
 * @author Adrián Ochoa Martínez
 */
public class UserService {

    /**
     * this method will get the input data and send it to the data base handler to create a new user.
     * It's used in the userAdd jsp.
     * @param userName
     * @param password
     * @param rol
     * @param nombre
     * @param apP
     * @param apM
     * @param email
     * @param estatus
     * @return true if the user was createde successfully, false otherwise
     */
    public boolean addUser(String userName, String password, String rol,
            String nombre, String apP, String apM, String email, String estatus) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper();
        dataBaseHelper.getConnection();
        if(dataBaseHelper.createUser(userName, password, rol, nombre, apP, apM, email, estatus)) {
            dataBaseHelper.closeConnection();
            return true;
        }
        return false;
    }
    
    /**
     * This method will deactivate an especific user.
     * It's used in the userDelete jsp.
     * @param userName
     * @return true if the user was successfully deleted, false otherwise
     */
    public boolean deleteUser (String userName) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper();
        dataBaseHelper.getConnection();
        if(dataBaseHelper.deleteUserByUserName(userName)) {
            dataBaseHelper.closeConnection();
            return true;
        }
        return false;
    }
    
    /**
     * This method returns a list with the active user names
     * It's used in the userDelete jsp.
     * @return 
     */
    public List<String> getActiveUserNames (String userID) {
        List<String> users = new ArrayList();
        DataBaseHelper dataBaseHelper = new DataBaseHelper();
        dataBaseHelper.getConnection();
        users = dataBaseHelper.getActiveUserNames(userID);
        dataBaseHelper.closeConnection();
        return users;
    }
    
    /**
     * This method returns a list with all the userNames
     * It's used in the editUser jsp.
     * @return 
     */
    public List<String> getUserNames () {
        List<String> users = new ArrayList<String>();
        DataBaseHelper dataBaseHelper = new DataBaseHelper();
        dataBaseHelper.getConnection();
        users = dataBaseHelper.getAllUserNames();
        dataBaseHelper.closeConnection();
        return users;
    }
    
}
