<%-- 
    Document   : uploadFiles
    Created on : 10-may-2017, 7:16:55
    Author     : Adrián Ochoa Martínez
--%>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Subir archivo data warehouse</title>
        
        <!-- Javascript core -->
        <script src="${pageContext.request.contextPath}/js/jquery.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>

        <!-- Bootstrap core -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css">
        
        <!-- Customized files -->
        <script src='${pageContext.request.contextPath}/js/uploadFiles.js'></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/uploadFile.css" type="text/css">
        
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
        <!-- NAVBAR -->
        <%@include file="/WEB-INF/jspf/navbar.jspf" %>
        
        <!--Container for ROC-->
        <div class="container">
            <div class="panel panel-default">
                <div class="panel-heading"><strong>Subir Archivo</strong></div>
                <div class="panel-body">

                    <!-- Standar Form -->
                    <h4>Selecciona archivo Data Warehouse</h4>
                    <form action="${pageContext.request.contextPath}/UploadDataFileServlet" method="post" enctype="multipart/form-data" id="js-upload-form">
                        <div class="form-inline">
                            <div class="form-group">
                                <input type="file" name="file" id="js-upload-files" required>
                            </div>
                            <button type="submit" class="btn btn-sm btn-primary" id="js-upload-submit">Subir Archivo</button>
                        </div>
                    </form>

                    <!-- Drop Zone -->
                    <h4>O arrastra archivos aquí</h4>
                    <div class="upload-drop-zone" id="drop-zone">
                        Arrastra archivos aquí
                    </div>

                    <!-- Progress Bar -->
                    <div class="progress">
                        <div class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;">
                            <span class="sr-only">0% Complete</span>
                        </div>
                    </div>

                    <!-- Upload Finished -->
                    <div class="js-upload-finished">
                        <h3>Archivo procesado</h3>
                        <div class="list-group">
                            <a href="#" class="list-group-item list-group-item-success"><span class="badge alert-success pull-right">Terminado</span>roc_agosto.xlsx</a>
                        </div>
                    </div>
                </div>
            </div>
        </div> <!-- /container -->
        
    </body>
</html>
