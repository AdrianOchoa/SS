<%-- 
    Document   : altaCriterio
    Created on : 01-jun-2017, 11:03:35
    Author     : Adrián Ochoa Martínez
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alta de Criterio</title>
        
        <!-- Javascript core -->
        <script src="${pageContext.request.contextPath}/js/jquery.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>

        <!-- Bootstrap core -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css">
        
        <!-- customized files -->
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
            <form class="well form-horizontal" method="post"  action="${pageContext.request.contextPath}/CriteriaAddServlet" id="contact_form">
                <fieldset>
                    <!-- Form Name -->
                    <legend>Solicitud de nuevo criterio lógico</legend>

                    <!-- Text input for new ID-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" >ID Nuevo</label> 
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input name="new_id" placeholder="ID Nuevo" class="form-control"  type="text">
                            </div>
                        </div>
                    </div>

                    <!-- Text input for old ID(if exists)-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" >ID Viejo (si existe)</label> 
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input name="old_id" placeholder="ID Viejo" class="form-control"  type="text">
                            </div>
                        </div>
                    </div>

                    <!-- Select basic for estatus-->
                    <div class="form-group">
                        <label class="col-md-4 control-label">Estatus</label>
                        <div class="col-md-4 selectContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <select name="estatus" class="form-control selectpicker" >
                                    <option value="" >Seleccione el estatus
                                    </option>
                                    <option>Activo</option>
                                    <option>Inactivo</option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <!-- Select basic for departamento-->
                    <div class="form-group">
                        <label class="col-md-4 control-label">Departamento</label>
                        <div class="col-md-4 selectContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <select name="departamento" class="form-control selectpicker" >
                                    <option value=" " >Seleccione el departamento
                                    </option>
                                    <option>Análisis</option>
                                    <option>Garantías</option>
                                    <option>Post-venta</option>
                                    <option>Seguridad</option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <!-- Select basic for tipo-->
                    <div class="form-group">
                        <label class="col-md-4 control-label">Tipo</label>
                        <div class="col-md-4 selectContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <select name="tipo" class="form-control selectpicker" >
                                    <option value=" " >Seleccione el tipo
                                    </option>
                                    <option>Análisis</option>
                                    <option>Campaña</option>
                                    <option>Inteligente</option>
                                    <option>Política</option>
                                    <option>Otro</option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <!-- Select basic for nivel-->
                    <div class="form-group">
                        <label class="col-md-4 control-label">Nivel</label>
                        <div class="col-md-4 selectContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <select name="nivel" class="form-control selectpicker" >
                                    <option value=" " >Seleccione el nivel
                                    </option>
                                    <option>Aviso al dealer</option>
                                    <option>Aviso al importador</option>
                                    <option >Error al dealer</option>
                                    <option >Error al importador</option>
                                    <option >Nota al dealer</option>
                                    <option >Nota al importador</option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <!-- Text area for objetivo-->
                    <div class="form-group">
                        <label class="col-md-4 control-label">Objetivo</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                                <textarea class="form-control" name="objetivo" placeholder="Objetivo"></textarea>
                            </div>
                        </div>
                    </div>

                    <!-- Text area for contenido-->
                    <div class="form-group">
                        <label class="col-md-4 control-label">Contenido</label>
                        <div class="col-md-4 selectContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <select name="contenido" class="form-control selectpicker" >
                                    <option value=" " >Seleccione el contenido
                                    </option>
                                    <option>Buy Back</option>
                                    <option>Campaing Number</option>
                                    <option>Claim Value</option>
                                    <option>Concerning Claim Type</option>
                                    <option>Concerning dealer or group of delaers</option>
                                    <option>Concerning Mobility Guarantee</option>
                                    <option>Concerning single vehicles VINs</option>
                                    <option>Concerning Warranty Code to Vehicle corresponding to CARPOT</option>
                                    <option>Criteria that cannot be placed in another criterion group</option>
                                    <option>Damage Type/Damage Location</option>
                                    <option>Goodwill</option>
                                    <option>Importer's Individual Criteria Switched by the Manufacturer</option>
                                    <option>Labour Position</option>
                                    <option>Manufacturer code of the damage causing part</option>
                                    <option>Material/Outside Material</option>
                                    <option>PR-Number</option>
                                    <option>Service Number</option>
                                    <option>Special Damage Number</option>
                                    <option>TPI</option>
                                    <option>Wear and Tear</option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <!-- Text area for comentario-->
                    <div class="form-group">
                        <label class="col-md-4 control-label">Comentario</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                                <textarea class="form-control" name="comentario" placeholder="Comentario"></textarea>
                            </div>
                        </div>
                    </div>

                    <!-- Text area for datos a detener-->
                    <div class="form-group">
                        <label class="col-md-4 control-label">Datos a detener</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                                <textarea class="form-control" name="datos_a_detener" placeholder="Datos a detener"></textarea>
                            </div>
                        </div>
                    </div>

                    <!-- Text input for averia-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" >Avería</label> 
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input name="averia" placeholder="Averia" class="form-control"  type="text">
                            </div>
                        </div>
                    </div>

                    <!-- Text input for daño-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" >Daño</label> 
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input name="dano" placeholder="Daño" class="form-control"  type="text">
                            </div>
                        </div>
                    </div>

                    <!-- Select Basic for marca -->
                    <div class="form-group">
                        <label class="col-md-4 control-label">Marca</label>
                        <div class="col-md-4 selectContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <select name="marca" class="form-control selectpicker" >
                                    <option value=" " >Seleccione la marca
                                    </option>
                                    <option>Volkswagen</option>
                                    <option>Audi</option>
                                    <option>Seat</option>
                                    <option>NTFZ</option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <!-- Text area for tipo auto - daño comercial-->
                    <div class="form-group">
                        <label class="col-md-4 control-label">Clave comercial</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                                <textarea class="form-control" name="clave_comercial" placeholder="Tipo de auto/Daño comercial"></textarea>
                            </div>
                        </div>
                    </div>

                    <!-- Text input for año modelo-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" >Año modelo</label> 
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input name="modelo" placeholder="Año modelo" class="form-control"  type="text">
                            </div>
                        </div>
                    </div>

                    <!-- Text area for tipo de garantía que afecta-->
                    <div class="form-group">
                        <label class="col-md-4 control-label">Tipo de garantía que afecta</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                                <textarea class="form-control" name="tipo_garantia" placeholder="Tipo de garantía que afecta"></textarea>
                            </div>
                        </div>
                    </div>

                    <!-- Text input for solicitante-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" >Solicitante</label> 
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input name="solicitante" placeholder="Solicitante" class="form-control"  type="text">
                            </div>
                        </div>
                    </div>
                    
                    <!-- Text input for agregado por -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" >Agregado por</label> 
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input name="agregado_por" placeholder="Agregado por" class="form-control"  type="text">
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
                            <button type="submit" class="btn btn-success" >Solicitar <span class="glyphicon glyphicon-send"></span></button>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>

        <div class="clearfix">
        </div>
    </body>
</html>
