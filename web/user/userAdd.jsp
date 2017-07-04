<%-- 
    Document   : addUser
    Created on : 02-jun-2017, 8:55:53
    Author     : Adrián Ochoa Martínez
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alta de Usuario</title>

        <!-- Javascript core -->
        <script src="${pageContext.request.contextPath}/js/jquery.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>

        <!-- Bootstrap core -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css">

        <!-- customized files -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/altaCriterio.css" type="text/css">

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

        <!--Main Section -->
        <div class="container">
            <form class="well form-horizontal" method="post"  action="${pageContext.request.contextPath}/UserAddServlet" id="contact_form">
                <fieldset>
                    <!-- Form Name -->
                    <legend>Alta de usuario nuevo</legend>

                    <!-- Text input for user name -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" >Nombre de Usuario</label> 
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input name="user" placeholder="Nombre de Usuario" class="form-control"  type="text">
                            </div>
                        </div>
                    </div>

                    <!-- Select basic for rol -->
                    <div class="form-group">
                        <label class="col-md-4 control-label">Tipo de Usuario</label>
                        <div class="col-md-4 selectContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <select name="tipo" class="form-control selectpicker" >
                                    <option value=" " >Seleccione el tipo de usuario
                                    </option>
                                    <option>Administrador</option>
                                    <option>Usuario</option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <!-- Text input for password -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" >Contraseña</label> 
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input name="pass" placeholder="Contraseña" class="form-control"  type="password">
                            </div>
                        </div>
                    </div>

                    <!-- Text input for confirm password -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" >Confirmar Contraseña</label> 
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input name="confirm_pass" placeholder="Confirmar contraseña" class="form-control"  type="password">
                            </div>
                        </div>
                    </div>

                    <!-- Text input for nombre -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" >Nombre</label> 
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input name="nombre" placeholder="Nombre" class="form-control"  type="text">
                            </div>
                        </div>
                    </div>

                    <!-- Text input for Apellido P-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" >Apellido paterno</label> 
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input name="ap_p" placeholder="Apellido paterno" class="form-control"  type="text">
                            </div>
                        </div>
                    </div>

                    <!-- Text input for Apellido M-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" >Apellido materno</label> 
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input name="ap_m" placeholder="Apellido meterno" class="form-control"  type="text">
                            </div>
                        </div>
                    </div>

                    <!-- Text input for email -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" >Email</label> 
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input name="email" placeholder="Email" class="form-control"  type="text">
                            </div>
                        </div>
                    </div>

                    <!-- Select basic for estatus -->
                    <div class="form-group">
                        <label class="col-md-4 control-label">Estatus del Usuario</label>
                        <div class="col-md-4 selectContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <select name="estatus" class="form-control selectpicker" >
                                    <option value=" " >Seleccione el estatus del usuario
                                    </option>
                                    <option>Activo</option>
                                    <option>Inactivo</option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <!-- Success message -->
                    <div class="alert alert-success" role="alert" id="success_message">Success <i class="glyphicon glyphicon-thumbs-up"></i> 							Thanks for contacting us, we will get back to you shortly.
                    </div>


                    <!-- Button -->
                    <div class="form-group">
                        <label class="col-md-4 control-label"></label>
                        <div class="col-md-4">
                            <button type="submit" class="btn btn-success" >Crear <span class="glyphicon glyphicon-send"></span></button>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>

        <div class="clearfix">
        </div>
    </body>
</html>
