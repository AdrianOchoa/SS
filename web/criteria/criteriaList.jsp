<%-- 
    Document   : criteriaList
    Created on : 02-jun-2017, 16:00:15
    Author     : Adrián Ochoa Martínez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Criterios</title>

        <!-- Javascript core -->
        <script src="${pageContext.request.contextPath}/js/jquery.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>

        <!-- Bootstrap core -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css">

        <!-- customized files -->
        <script src="${pageContext.request.contextPath}/js/table.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/tableToExcel.js" type="text/javascript"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css" type="text/css">

        <!-- check session script -->
        <script type="text/javascript">
            <c:if test="${empty logged}" >
            window.location = "http://localhost:8080/SS/forbidden.jsp";
            </c:if>
        </script>
    </head>
    <body>
        <!-- jsp calling to servlet -->
        <jsp:include page="${pageContext.request.contextPath}/../CriteriaGetCriteriaServlet" />

        <!-- NAVBAR -->
        <%@include file="/WEB-INF/jspf/navbar.jspf" %>

        <!-- BODY -->
        <div class="container">
            <hr>
            <div class="row">
                <!--<div class="panel panel-primary filterable">-->
                <div class="panel panel-primary">

                    <div class="panel-heading">
                        <h3 class="panel-title">Criterios</h3>
                    </div>
                    <div class="container-fluid">
                        <div class="table-responsive">
                        <table class="table table-responsive table-bordered table-sm table-hover" id="tableData">
                            <thead>
                                <tr>
                                    <!--<tr class="filters">-->
                                    <th class="text-center">ID</th>
                                    <th class="text-center">ID NUEVO</th>
                                    <th class="text-center">ID VIEJO</th>
                                    <th class="text-center">ESTATUS</th>
                                    <th class="text-center">DEPARTAMENTO</th>
                                    <th class="text-center">TIPO</th>
                                    <th class="text-center">NIVEL</th>
                                    <th class="text-center">OBJETIVO</th>
                                    <th class="text-center">GRUPO</th>
                                    <th class="text-center">CONTENIDO</th>
                                    <th class="text-center">COMENTARIO</th>
                                    <th class="text-center">DATOS A DETENER</th>
                                    <th class="text-center">AVERÍA</th>
                                    <th class="text-center">DAÑO</th>
                                    <th class="text-center">MARCA</th>
                                    <th class="text-center">CLAVE COMERCIAL</th>
                                    <th class="text-center">MODELO</th>
                                    <th class="text-center">GARANTÍA QUE AFECTA</th>
                                    <th class="text-center">SOLICITANTE</th>
                                    <th class="text-center">FECHA DE CREACIÓN</th>
                                    <th class="text-center">FECHA DE REVISIÓN</th>
                                    <th class="text-center">PERIODO DE REVISIÓN</th>
                                    <th class="text-center">AGREGADO POR</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="element" items="${criterios}">
                                    <tr>
                                        <td class="text-center">${element.criterioID}</td>
                                        <td class="text-center">${element.idNuevo}</td>
                                        <td class="text-center">${element.idViejo}</td>
                                        <td class="text-center">${element.estatus}</td>
                                        <td class="text-center">${element.departamento}</td>
                                        <td class="text-center">${element.tipo}</td>
                                        <td class="text-center">${element.nivel}</td>
                                        <td class="text-center">${element.objetivo}</td>
                                        <td class="text-center">${element.grupo}</td>
                                        <td class="text-center">${element.contenido}</td>
                                        <td class="text-center">${element.comentario}</td>
                                        <td class="text-center">${element.datosDetener}</td>
                                        <td class="text-center">${element.averia}</td>
                                        <td class="text-center">${element.danio}</td>
                                        <td class="text-center">${element.marca}</td>
                                        <td class="text-center">${element.claveComercial}</td>
                                        <td class="text-center">${element.modelo}</td>
                                        <td class="text-center">${element.garantiaAfecta}</td>
                                        <td class="text-center">${element.solicitante}</td>
                                        <td class="text-center">${element.fechaCreacion}</td>
                                        <td class="text-center">${element.fechaRevision}</td>
                                        <td class="text-center">${element.periodoRevision}</td>
                                        <td class="text-center">${element.agregadoPor}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                           </div>
                </div>
            </div>
            <div class="row">
                <a id="dLink" style="display: none"></a>
                <a class="btn btn-success" onclick="tableToExcel('tableData', 'Criterios', 'lista_criterios.xls');">Exportar</a>
            </div>
        </div>
    </body>
</html>
