/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vw.servlet.upload;

import com.vw.service.UploadService;
import com.vw.util.ExcelConverter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Adrián Ochoa Martínez
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 20,
        maxRequestSize = 1024 * 1024 * 50)
@WebServlet("/UploadEasyFileServlet")
public class UploadEasyFileServlet extends HttpServlet {

    private static final String SAVE_DIR = "uploadFiles";
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //gets absolute path of application
        String appPath = request.getServletContext().getRealPath("");
        //Construct the directory
        String savePath = appPath + File.separator + SAVE_DIR;
        
        File fileSaveDir = new File(savePath);
        
        if(!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        
        String fullFile = null;
        String fileName = null;
        String fileExtension = null;
        
        for(Part part : request.getParts()) {
            fileName = extractFileName(part);
            fileExtension = fileName.split("[.]+")[1];
            fileName = new File(fileName).getName();
            fullFile = savePath + File.separator + fileName;
            part.write(fullFile);
        }
        
        XSSFWorkbook xssfw = null;
        
        if (fileExtension.toLowerCase().equals("xls")) {
            try {
                //            xssfw = ExcelConverter.convertHSSFWorkbookToXSSFWorkbook(
                //                    new HSSFWorkbook(new FileInputStream(fullFile)));
                xssfw = new ExcelConverter(
                        new HSSFWorkbook(new FileInputStream(fullFile))).call();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else if(fileExtension.toLowerCase().equals("xlsx")){
            xssfw = new XSSFWorkbook(new FileInputStream(fullFile));
        }
        
        UploadService uploadService = new UploadService(xssfw);
        
        HttpSession session;
        String nextPage;
        if(uploadService.uploadFile("easy", "", "")) {
            nextPage = "home.jsp";
            session = request.getSession();
            session.setAttribute("message", "El archivo se procesó correctamente.");
        } else {
            nextPage = "error.jsp";
            session = request.getSession();
            session.setAttribute("errorMessage", "No se pudo procesar el archivo.");
        }
        File file = new File(fullFile);
        file.delete();
        request.getRequestDispatcher(nextPage).forward(request, response);
    }
    
    private String extractFileName (Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String [] items = contentDisp.split(";");
        for (String s : items) {
            if(s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() -1);
            }
        }
        return "";
    }
    
}
