<%-- 
    Document   : forbbiden
    Created on : 01-jun-2017, 10:01:34
    Author     : VW
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forbbiden</title>
        <link rel="stylesheet" href="css/bootstrap.min.cdn.css" type="text/css">
        <link rel="stylesheet" href="css/font-awesome.min.cdn.css" type="text/css">
        <link rel="stylesheet" href="css/forbidden.css" type="text/css">
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.cdn.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="container">
            <div class="jumbotron">
                <h1><i class="fa fa-frown-o red"></i>Acceso Prohibido</h1>
                <p class="lead">No cuentas con permisos para realizar esta operación.<em><span id="display-domain"></span></em>.</p>
                <p>
                    <a onclick=javascript:login(); class="btn btn-default btn-lg green">Iniciar Sesión</a>
                    <script type="text/javascript">
                        function login() {
                            window.location = "http://localhost:8080/SS";
                        }
                    </script>
                </p>
            </div>
        </div>
        <div class="container">
            <div class="body-content">
                <div class="row">
                    <div class="col-md-6">
                        <h2>¿Qué ocurrio?</h2>
                        <p class="lead">Este error indica que no cuenta con los privilegios necesarios para acceder al sitio web.</p>
                    </div>
                    <div class="col-md-6">
                        <h2>¿Qué puedo hacer?</h2>
                        <p class="lead">Si eres un usuario:</p>
                        <p>Por favor verifica que estás dado de alta en el sistema.</p>
                        <p class="lead">Si eres administrador:</p>
                        <p>Por favor verifica el acceso a internet.</p>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
