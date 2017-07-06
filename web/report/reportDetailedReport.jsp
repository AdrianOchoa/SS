<%-- 
    Document   : reportDetailedReport
    Created on : 28-jun-2017, 22:37:56
    Author     : Adrián
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reporte detallado</title>

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
            <c:choose>
                <c:when test="${empty logged}" >
                    window.location = "http://localhost:8080/SS/forbidden.jsp";
                </c:when>
                <c:when test="${rol != 'admin'}">
                    <c:if test="${rol != 'bussiness'}">
                        window.location = "http://localhost:8080/SS/forbidden.jsp";
                    </c:if>
                </c:when>
            </c:choose>
        </script>
    </head>
    <body>
        <!-- NAVBAR -->
        <%@include file="/WEB-INF/jspf/navbar.jspf" %>

        <!-- BODY -->
        <div class="container">
            <!-- A partir de aquí la tabla -->
            <c:if test="${not empty result}">
                <div class="row">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Resultados</h3>
                        </div>
                        <table class="table table-responsive table-bordered" id="tableData">
                            <thead>
                                <tr>
                                    <th class="text-center text-nowrap">ID DE RECLAMACION</th>
                                    <th class="text-center text-nowrap">MONTO</th>
                                    <th class="text-center text-nowrap">SOLICITANTE</th>
                                    <th class="text-center text-nowrap">CHASIS</th>
                                    <th class="text-center text-nowrap">CRITERIO</th>
                                    <th class="text-center text-nowrap">SERIAL</th>
                                    <th class="text-center text-nowrap">DEALER</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="element" items="${result}">
                                    <tr>
                                        <td class="text-center text-nowrap">${element.id}</td>
                                        <td class="text-center text-nowrap">${element.monto}</td>
                                        <td class="text-center text-nowrap">${element.solicitante}</td>
                                        <td class="text-center text-nowrap">${element.chasis}</td>
                                        <td class="text-center text-nowrap">${element.criteriaID}</td>
                                        <td class="text-center text-nowrap">${element.claimSerial}</td>
                                        <td class="text-center text-nowrap">${element.dealer}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="row">
                    <a id="dLink" style="display: none"></a>
                    <a class="btn btn-success" onclick="tableToExcel('tableData', 'Resultados', 'ajuste_analista.xls');">Exportar</a>
                </div>
            </c:if>
        </div>¨
    </body>
</html>
