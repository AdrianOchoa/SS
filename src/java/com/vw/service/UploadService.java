/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vw.service;

import com.vw.util.ExcelFileHandler;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Adrián Ochoa Martínez
 */
public class UploadService {
    
    private XSSFWorkbook xssfw;
    
    public UploadService (XSSFWorkbook xssfw) {
        this.xssfw = xssfw;
    }

    public boolean uploadFile(String fileType, String month, String year) {
        if(fileType.equals("easy")) {
            if(new ExcelFileHandler(xssfw).processEasyFile()) {
                return true;
            }
        } else if(fileType.equals("data")) {
            if(new ExcelFileHandler(xssfw).processDwhFile()) {
                return true;
            }
        } else if (fileType.equals("roc")) {
            if(new ExcelFileHandler(xssfw).processRocFile()) {
                return true;
            } 
        } else if (fileType.equals("claim")) {
            if(new ExcelFileHandler(xssfw).processClaimFile()) {
                return true;
            }
        }  else if(fileType.equals("roc_mensual")) {
            if(new ExcelFileHandler(xssfw).processMonthlyRocFile(month, year)) {
                return true;
            }
        }
        return false;
    }
}
