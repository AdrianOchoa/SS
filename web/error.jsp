<%-- 
    Document   : error
    Created on : 01-jun-2017, 15:37:11
    Author     : VW
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
        <link rel="stylesheet" href="css/bootstrap.min.cdn.css" type="text/css">
        <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">-->
        <link rel="stylesheet" href="css/font-awesome.min.cdn.css" type="text/css">
        <link rel="stylesheet" href="css/error.css" type="text/css">
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.cdn.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="container">
            <div class="jumbotron">
                <h1><span class="glyphicon glyphicon-fire red"></span> 500 internal server error</h1>
                <p class="lead">El servidor web está regresando un error<em><span id="display-domain"></span></em>.</p>
                        <c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger">
                        <c:out value="${errorMessage}" />
                    </div>
                </c:if>
                <p>
                    <a onclick=javascript:home(); class="btn btn-default btn-lg green">Regresar...</a>
                    <script type="text/javascript">
                        function home() {
                            window.location = "http://localhost:8080/SS/home.jsp";
                        }
                    </script>
                </p>
            </div>
        </div>
        <div class="container">
            <div class="body-content">
                <div class="row">
                    <div class="col-md-6">
                        <h2>¿Qué ocurrió?</h2>
                        <p class="lead">Un código de error 500 significa que hay un problema con el servidor que causó un mal funcionamiento.</p>
                    </div>
                    <div class="col-md-6">
                        <h2>¿Qué puedo hacer?</h2>
                        <p class="lead">Si eres un usuario</p>
                        <p> nada por el momento. Consulta con el asesor técnico sobre la falla.</p>
                        <p class="lead">Si eres el administrador</p>
                        <p> contacta con los proveedores del servicio o internet.</p>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
