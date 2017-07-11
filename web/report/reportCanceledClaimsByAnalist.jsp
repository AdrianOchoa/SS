<%-- 
    Document   : reportResult
    Created on : 01-jun-2017, 14:10:09
    Author     : VW
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reporte cancelación por analista</title>

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
        <!-- jsp calling to servlet -->
        <jsp:include page="${pageContext.request.contextPath}/../ClearServlet" />
        
        <!-- NAVBAR -->
        <%@include file="/WEB-INF/jspf/navbar.jspf" %>

        <!-- BODY -->
        <div class="container">
            <hr>

            <form class="well form-horizontal" method="post"  action="${pageContext.request.contextPath}/ReportCanceledClaimsByAnalistServlet">
                <h3>Seleccione la fecha de inicio del reporte:</h3>
                <div class="form-group">
                    <div class="col-md-4 selectContainer">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
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
                                <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
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
                            <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                            <select name="mesFin" class="form-control selectpicker" required>
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
                                <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
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
                        <table class="table table-responsive table-bordered" id="tableData">
                            <thead>
                                <tr>
                                    <th class="text-center text-nowrap">ID</th>
                                    <th class="text-center text-nowrap">TIPO DE CRITERIO</th>
                                    <th class="text-center text-nowrap"># DE HITS</th>
                                    <th class="text-center text-nowrap">MONTO</th>
                                    <th class="text-center text-nowrap">SOLICITANTE</th>
                                    <th class="text-center text-nowrap">MARCA</th>
                                    <th class="text-center text-nowrap">NIVEL</th>
                                    <th class="text-center text-nowrap">DIAS DE ACTIVACION</th>
                                    <th class="text-center text-nowrap">DETALLES</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="element" items="${result}">
                                    <form method="post" action="${pageContext.request.contextPath}/ReportGetDetailedReport">
                                    <tr>
                                        <td>${element.id}</td>
                                        <td>${element.tipoCriterio}</td>
                                        <td>${element.numeroHits}</td>
                                        <td>${element.monto}</td>
                                        <td>${element.solicitante}</td>
                                        <td>${element.marca}</td>
                                        <td class="text-center text-nowrap">${element.level}</td>
                                        <td>${element.diasActivacion}</td>
                                        <td class="text-center text-nowrap">
                                            <input name="id" type="hidden" value="${element.id}">
                                            <input name="type" type="hidden" value="canceladosAnalista">
                                            <input name="initialDate" type="hidden" value="${initialDate}">
                                            <input name="finalDate" type="hidden" value="${finalDate}">
                                            <input name="applicant" type="hidden" value="${element.solicitante}">
                                            <button type="submit" class="btn btn-success">Ver más</button>
                                        </td>
                                    </tr>
                                    </form>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="row">
                    <a id="dLink" style="display: none"></a>
                    <a class="btn btn-success" onclick="tableToExcel('tableData', 'Resultados', 'cancelaciones_analista.xls');">Exportar</a>
                </div>
            </c:if>
        </div>
    </body>
</html>
