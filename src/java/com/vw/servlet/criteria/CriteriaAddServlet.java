/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vw.servlet.criteria;

import com.vw.service.CriteriaService;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This servlet will allow an user to add a new criteria (but is not approved yet)
 * @author Adrián Ochoa Martínez
 */
@WebServlet("/CriteriaAddServlet")
public class CriteriaAddServlet extends HttpServlet {
    
    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        //this variable will contain the next page, depending on the result
        String nextPage;
        
        //getting the required fields
        String idNuevo = request.getParameter("new_id").trim();
        String idViejo = request.getParameter("old_id").trim();
        String idGenerado = idNuevo + "*" + idViejo;
        String estatus = request.getParameter("estatus").toLowerCase().trim();
        String departamento = request.getParameter("departamento").toLowerCase().trim();
        String tipo = request.getParameter("tipo").toLowerCase().trim();
        String nivel = request.getParameter("nivel").toLowerCase().trim();
        String objetivo = request.getParameter("objetivo").trim();
        String contenido = request.getParameter("contenido").toLowerCase().trim();
        String grupo = getGrupoFromContenido(contenido);
        String comentario = request.getParameter("comentario").trim();
        String datos = request.getParameter("datos_a_detener").trim();
        String averia = request.getParameter("averia").trim();
        String danio = request.getParameter("dano").trim();
        String marca = request.getParameter("marca").trim().toLowerCase();
        String claveComercial = request.getParameter("clave_comercial");
        String modelo = request.getParameter("modelo").trim();
        String tiposGarantia = request.getParameter("tipo_garantia").trim();
        String solicitante = request.getParameter("solicitante").trim();
        String fechaCreacion = new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(new Date());
        String fechaRevision = new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(new Date());
        int periodo = 365;
        String agregadoPor = request.getParameter("agregado_por").trim();
        String level = request.getParameter("lev").toLowerCase().trim();
        
        //creating the service
        CriteriaService criteriaService = new CriteriaService();
        
        //If the service was successfully created, the user will be redirected to the home page with a success message.
        //Otherwise, it will be redirected to an error page.
        HttpSession session;
        if(criteriaService.addCriteria(idGenerado, idNuevo, idViejo, estatus, 
                departamento, tipo, nivel, objetivo, grupo, contenido, comentario, 
                datos, averia, danio, marca, claveComercial, modelo, tiposGarantia, 
                solicitante, fechaCreacion, fechaRevision, periodo, agregadoPor, level)) {
            session = request.getSession();
            session.setAttribute("message", "El criterio se solicitó correctamente.");
            nextPage = "home.jsp";
        } else {
            session = request.getSession();
            session.setAttribute("errorMessage", "No se pudo solicitar el criterio.");
            nextPage = "error.jsp";
        }
        request.getRequestDispatcher(nextPage).forward(request, response);
    }
    
    private String getGrupoFromContenido (String contenido) {
        HashMap<String, String> map = new HashMap();
        map.put("campaign number", "A");
        map.put("labour position", "B");
        map.put("pr-number", "C");
        map.put("damage type/damage location", "D");
        map.put("manufacturer code of the damage causing part", "E");
        map.put("tpi", "F");
        map.put("material/outside material", "G");
        map.put("wear and tear", "H");
        map.put("claim value", "I");
        map.put("concerning single vehicles vins", "J");
        map.put("buy back", "K");
        map.put("goodwill", "L");
        map.put("concerning dealer or group of dealers", "M");
        map.put("concerning warranty code to vehicle corresponding to carpot", "N");
        map.put("concerning claim type", "O");
        map.put("service number", "P");
        map.put("concerning mobility guarantee", "Q");
        map.put("special damage number", "R");
        map.put("importer's individual criteria switched by the manufacturer", "S");
        map.put("criteria that cannot be placed in another criterion group", "Z");
        return map.get(contenido);
    }
    
}
