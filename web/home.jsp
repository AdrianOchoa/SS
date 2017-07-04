<%-- 
    Document   : home
    Created on : 01-jun-2017, 9:00:32
    Author     : Adrián Ochoa Martínez
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>

        <!-- Javascript core -->
        <script src="js/jquery.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>

        <!-- Bootstrap core -->
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        
        <!-- check session script -->
        <script type="text/javascript">
            <c:if test="${empty logged}" >
                window.location = "http://localhost:8080/SS/forbidden.jsp";
            </c:if>
        </script>
    </head>
    <body>
        <!-- NAVBAR -->
        <%@include file="/WEB-INF/jspf/navbar.jspf" %>

        <!-- BODY -->
        <div class="container">
            <c:if test="${not empty message}">
                <div class="alert alert-success">
                    <c:out value="${message}" />
                </div>
            </c:if>
        </div>
    </body>
</html>
