/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vw.servlet.upload;

import com.vw.model.MissingCriteria;
import com.vw.service.CriteriaService;
import com.vw.service.UploadService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Adrián Ochoa Martínez
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 20,
        maxRequestSize = 1024 * 1024 * 50)
@WebServlet("/UploadMonthlyRocFileServlet")
public class UploadMonthlyRocFileServlet extends HttpServlet {

    private static final String SAVE_DIR = "uploadFiles";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        //gets absolute path of application
        String appPath = request.getServletContext().getRealPath("");
        //Construct the directory
        String savePath = appPath + File.separator + SAVE_DIR;

        File fileSaveDir = new File(savePath);

        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        String fullFile = null;
        String fileName = null;

        for (Part part : request.getParts()) {
            fileName = extractFileName(part);
            if (fileName == null) {
                break;
            }
            if (fileName != null) {
                if (fileName.isEmpty()) {
                    break;
                }
            }
            fullFile = savePath + File.separator + fileName;
            part.write(fullFile);
        }


        XSSFWorkbook xssfw = null;

        xssfw = new XSSFWorkbook(new FileInputStream(fullFile));

        UploadService uploadService = new UploadService(xssfw);

        HttpSession session;
        String nextPage;

        String month = getMonth(request.getParameter("mes").toLowerCase());
        String year = request.getParameter("anio");

        if (uploadService.uploadFile("roc_mensual", month, year)) {
            List<MissingCriteria> criterios = new CriteriaService().getCriteriaDifference(month, year);
            if (criterios.isEmpty()) {
                nextPage = "home.jsp";
                session = request.getSession();
                session.setAttribute("message", "El archivo se procesó correctamente.");
            } else {
                nextPage = "criteria/missingCriteria.jsp";
                session = request.getSession();
                session.setAttribute("result", criterios);
            }
        } else {
            nextPage = "error.jsp";
            session = request.getSession();
            session.setAttribute("errorMessage", "No se pudo procesar el archivo.");
        }
        File file = new File(fullFile);
        file.delete();
        request.getRequestDispatcher(nextPage).forward(request, response);

    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

    private String getMonth(String month) {
        HashMap<String, String> map = new HashMap();
        map.put("enero", "01");
        map.put("febrero", "02");
        map.put("marzo", "03");
        map.put("abril", "04");
        map.put("mayo", "05");
        map.put("junio", "06");
        map.put("julio", "07");
        map.put("agosto", "08");
        map.put("septiembre", "09");
        map.put("octubre", "10");
        map.put("noviembre", "11");
        map.put("diciembre", "12");
        return map.get(month);
    }
}
