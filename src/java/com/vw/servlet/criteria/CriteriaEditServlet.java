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
 *
 * @author Adrián Ochoa Martínez
 */
@WebServlet("/CriteriaEditServlet")
public class CriteriaEditServlet extends HttpServlet {
    
    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        String nextPage;
        String id = request.getParameter("id").toUpperCase();
        String estatus = request.getParameter("estatus").toLowerCase();
        String departamento = request.getParameter("departamento").toLowerCase();
        String tipo = request.getParameter("tipo").toLowerCase();
        String nivel = request.getParameter("nivel").toLowerCase();
        String objetivo = request.getParameter("objetivo").toLowerCase();
        String contenido = request.getParameter("contenido").toLowerCase();
        String grupo = getGrupoFromContenido(contenido);
        String comentario = request.getParameter("comentario");
        String datos = request.getParameter("datos_a_detener");
        String averia = request.getParameter("averia");
        String danio = request.getParameter("dano");
        String marca = request.getParameter("marca");
        String claveComercial = request.getParameter("clave_comercial");
        String modelo = request.getParameter("modelo");
        String garantiaAfecta = request.getParameter("tipo_garantia");
        String fechaRevision = new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(new Date());
        
        CriteriaService criteriaService = new CriteriaService();
        
        HttpSession session;
        if(criteriaService.editCriteria(id, estatus, departamento, tipo, nivel, objetivo, grupo, contenido, comentario, datos, averia, danio, marca, claveComercial, modelo, garantiaAfecta, fechaRevision)) {
            nextPage = "home.jsp";
            session = request.getSession();
            session.setAttribute("message", "El criterio se editó correctamente.");
        } else {
            nextPage = "error.jsp";
            session = request.getSession();
            session.setAttribute("errorMessage", "No se pudo editar el criterio.");
        }
        request.getRequestDispatcher(nextPage).forward(request, response);
    }
    
    private String getGrupoFromContenido (String contenido) {
        HashMap<String, String> map = new HashMap();
        map.put("campaign number", "A");
        map.put("labour position", "B");
        map.put("pr-number", "C");
        map.put("damage type/location", "D");
        map.put("manufacturer code of the damage causing part", "E");
        map.put("tpi", "F");
        map.put("material/outside material", "G");
        map.put("wear and tear", "H");
        map.put("claim value", "I");
        map.put("concerning vehicles single vins", "J");
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
