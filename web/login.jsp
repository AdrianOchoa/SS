<%-- 
    Document   : login
    Created on : 01-jun-2017, 8:32:49
    Author     : Adrián Ochoa Martínez
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>

        <!-- Javascript core -->
        <script src="js/jquery.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>

        <!-- Bootstrap core -->
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">

        <!-- customized css -->
        <link rel='stylesheet prefetch' href='https://fonts.googleapis.com/css?family=Open+Sans:600'>
        <link rel="stylesheet" href="css/login-style.css" type="text/css">
        
        <!-- check session script -->
        <script type="text/javascript">
            <c:if test="${not empty logged}" >
                window.location = "http://localhost:8080/SS/home.jsp";
            </c:if>
        </script>
    </head>
    <body >
        <div class="login-wrap">
            <div class="login-html">
                <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Iniciar Sesión</label>
                <div class="login-form">
                    <!-- calling action servlet -->
                    <form method="post" action="LoginServlet">
                        <div class="sign-in-htm">
                            <div class="group">
                                </br>
                                </br>
                                <label for="user" class="label">Usuario</label>
                                <input name="user" type="text" class="input" required>
                            </div>
                            <div class="group">
                                </br>
                                <label for="pass" class="label">Contraseña</label>
                                <input name="pass" type="password" class="input" data-type="password" required>
                            </div>
                            <div class="group">
                                </br>
                                </br>
                                </br>
                                <input type="submit" class="button" value="Iniciar Sesión">
                            </div>
                            <div class="hr"></div>
                            <!-- error message if exists -->
                            <c:if test="${not empty errorMessage}">
                                <h5 class="alert alert-danger">
                                    <c:out value="${errorMessage}" />
                                </h5>
                            </c:if>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
