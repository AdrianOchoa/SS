<%-- 
    Document   : criteriaEditForm
    Created on : 19-jun-2017, 13:10:04
    Author     : Adrián Ochoa Martínez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edición de Criterio</title>

        <!-- Javascript core -->
        <script src="${pageContext.request.contextPath}/js/jquery.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>

        <!-- Bootstrap core -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css">

        <!-- Customized files -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/altaCriterio.css" type="text/css">

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

        <!--Main Section -->
        <div class="container">
            <form class="well form-horizontal" method="post" action="${pageContext.request.contextPath}/CriteriaAdminApprovedServlet" id="form" name="form">
                <fieldset>
                    <!-- Form Name -->
                    <legend>Criterio a aprobar ${id}</legend>

                    <input type="hidden" value="${id}" name="id">

                    <!-- Select basic for estatus-->
                    <div class="form-group">
                        <label class="col-md-4 control-label">Estatus</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                                <textarea class="form-control" name="estatus" placeholder="">${criterio.estatus}</textarea>
                            </div>
                        </div>
                    </div>

                    <!-- Select basic for departamento-->
                    <div class="form-group">
                        <label class="col-md-4 control-label">Departamento</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                                <textarea class="form-control" name="departamento" placeholder="">${criterio.departamento}</textarea>
                            </div>
                        </div>
                    </div>

                    <!-- Select basic for tipo-->
                    <div class="form-group">
                        <label class="col-md-4 control-label">Tipo</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                                <textarea class="form-control" name="tipo" placeholder="">${criterio.tipo}</textarea>
                            </div>
                        </div>
                    </div>

                    <!-- Text Area for nivel-->
                    <div class="form-group">
                        <label class="col-md-4 control-label">Nivel</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                                <textarea class="form-control" name="nivel" placeholder="">${criterio.nivel}</textarea>
                            </div>
                        </div>
                    </div>

                    <!-- Text area for objetivo-->
                    <div class="form-group">
                        <label class="col-md-4 control-label">Objetivo</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                                <textarea class="form-control" name="objetivo" placeholder="Objetivo">${criterio.objetivo}</textarea>
                            </div>
                        </div>
                    </div>

                    <!-- Text area for contenido-->
                    <div class="form-group">
                        <label class="col-md-4 control-label">Contenido</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                                <textarea class="form-control" name="contenido" placeholder="">${criterio.contenido}</textarea>
                            </div>
                        </div>
                    </div>

                    <!-- Text area for comentario-->
                    <div class="form-group">
                        <label class="col-md-4 control-label">Comentario</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                                <textarea class="form-control" name="comentario" placeholder="Comentario">${criterio.comentario}</textarea>
                            </div>
                        </div>
                    </div>

                    <!-- Text area for datos a detener-->
                    <div class="form-group">
                        <label class="col-md-4 control-label">Datos a detener</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                                <textarea class="form-control" name="datos_a_detener" placeholder="Datos a detener">${criterio.datosDetener}</textarea>
                            </div>
                        </div>
                    </div>

                    <!-- Text input for averia-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" >Avería</label> 
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <!--<input name="averia" placeholder="" class="form-control"  type="text">-->
                                <textarea class="form-control" name="averia">${criterio.averia}</textarea>
                            </div>
                        </div>
                    </div>

                    <!-- Text input for daño-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" >Daño</label> 
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <!--<input name="dano" placeholder="${criterio.danio}" class="form-control"  type="text">-->
                                <textarea class="form-control" name="averia">${criterio.danio}</textarea>
                            </div>
                        </div>
                    </div>

                    <!-- text area for marca -->
                    <div class="form-group">
                        <label class="col-md-4 control-label">Marca</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                                <textarea class="form-control" name="marca" placeholder="">${criterio.marca}</textarea>
                            </div>
                        </div>
                    </div>

                    <!-- Text area for tipo auto - daño comercial-->
                    <div class="form-group">
                        <label class="col-md-4 control-label">Clave comercial</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                                <textarea class="form-control" name="clave_comercial" placeholder="">${criterio.claveComercial}</textarea>
                            </div>
                        </div>
                    </div>

                    <!-- Text input for año modelo-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" >Año modelo</label> 
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <!--<input name="modelo" placeholder="${criterio.modelo}" class="form-control"  type="text">-->
                                <textarea class="form-control" name="modelo" placeholder="">${criterio.modelo}</textarea>
                            </div>
                        </div>
                    </div>

                    <!-- Text area for tipo de garantía que afecta-->
                    <div class="form-group">
                        <label class="col-md-4 control-label">Tipo de garantía que afecta</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                                <textarea class="form-control" name="tipo_garantia" placeholder="">${criterio.garantiaAfecta}</textarea>
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
                            <button type="submit" class="btn btn-success">Aprobar<span class="glyphicon glyphicon-send"></span></button>
                            </form>

                            <form class="well form-horizontal" method="post" action="${pageContext.request.contextPath}/CriteriaDeleteCriteriabyIDServlet" id="form" name="form">
                                <input type="hidden" value="${id}" name="id">
                                <button type="submit" class="btn btn-danger" >Eliminar<span class="glyphicon glyphicon-send"></span></button>
                            </form>

                        </div>
                    </div>
                </fieldset>
        </div>

        <div class="clearfix">
        </div>
    </body>
</html>
