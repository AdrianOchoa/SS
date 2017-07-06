<%-- 
    Document   : reportTotalClaimsByBrand
    Created on : 14-jun-2017, 13:19:41
    Author     : Adrián Ochoa Martínez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reporte de total de reclamos por marca</title>

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
                    window.location = "http://localhost:8080/SS/forbidden.jsp";
                </c:when>
            </c:choose>
        </script>
    </head>
    <body>
        <!-- jsp calling to servlet -->
        <jsp:include page="${pageContext.request.contextPath}/../ClearServlet" />
        
        <!-- NAVBAR -->
        <%@include file="/WEB-INF/jspf/navbar.jspf" %>

        <!-- BODY -->
        <div class="container">
            <hr>
            <form class="well form-horizontal" method="post"  action="${pageContext.request.contextPath}/ReportTotalClaimsByBrandServlet">
                <h3>Seleccione la fecha de inicio del reporte:</h3>
                <div class="form-group">
                    <div class="col-md-4 selectContainer">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                            <select name="mes" class="form-control selectpicker" required>
                                <option value="" >Seleccione el mes</option>
                                <option>Enero</option>
                                <option>Febrero</option>
                                <option>Marzo</option>
                                <option>Abril</option>
                                <option>Mayo</option>
                                <option>Junio</option>
                                <option>Julio</option>
                                <option>Agosto</option>
                                <option>Septiembre</option>
                                <option>Octubre</option>
                                <option>Noviembre</option>
                                <option>Diciembre</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-4 selectContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <select name="anio" class="form-control selectpicker" required>
                                    <option value="" >Seleccione el año</option>
                                    <option>2016</option>
                                    <option>2017</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                
                <br>
                <br>
                
                <legend>Seleccione la fecha del final del reporte:</legend>
                <div class="form-group">
                    <div class="col-md-4 selectContainer">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                            <select name="mesFin" class="form-control selectpicker" required>
                                <option value="" >Seleccione el mes</option>
                                <option>Enero</option>
                                <option>Febrero</option>
                                <option>Marzo</option>
                                <option>Abri</option>
                                <option>Mayo</option>
                                <option>Junio</option>
                                <option>Julio</option>
                                <option>Agosto</option>
                                <option>Septiembre</option>
                                <option>Octubre</option>
                                <option>Noviembre</option>
                                <option>Diciembre</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-4 selectContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <select name="anioFin" class="form-control selectpicker" required>
                                    <option value="" >Seleccione el año</option>
                                    <option>2016</option>
                                    <option>2017</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label"></label>
                    <div class="col-md-4">
                        <button type="submit" class="btn btn-success" >Aceptar<span class="glyphicon glyphicon-send"></span></button>
                    </div>
                </div>

            </form>
            <br>
            <br>

            <!-- A partir de aquí la tabla -->
            <c:if test="${not empty result}">
                <div class="row">
                    <div class="panel panel-primary filterable">
                        <div class="panel-heading">
                            <h3 class="panel-title">Resultados</h3>
                        </div>
                        <table class="table table-responsive table-bordered table-hover" id="tableData">
                            <thead>
                                <tr>
                                    <th class="text-center text-nowrap">TOTAL DE RECLAMACIONES</th>
                                    <th class="text-center text-nowrap">MARCA</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="element" items="${result}">
                                    <tr>
                                        <td id="totalClaims" class="text-center text-nowrap">${element.totalClaims}</td>
                                        <td id="brand" class="text-center text-nowrap">${element.brand}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="row">
                    <a id="dLink" style="display: none"></a>
                    <a class="btn btn-success" onclick="tableToExcel('tableData', 'Criterios', 'lista_criterios');">Exportar</a>
                </div>
            </c:if>
        </div>
    </body>
</html>
