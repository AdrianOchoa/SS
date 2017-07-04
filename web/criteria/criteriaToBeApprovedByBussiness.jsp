<%-- 
    Document   : criteriaToBeApprovedByBussiness
    Created on : 30-jun-2017, 13:35:44
    Author     : Adrián
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Aprobar criterio</title>
        
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
                <c:when test="${rol != 'bussiness'}">
                    window.location = "http://localhost:8080/SS/forbidden.jsp";
                </c:when>
            </c:choose>
        </script>
    </head>
    <body>
        <jsp:include page="${pageContext.request.contextPath}/../CriteriaGetNotBussinessApprovedCriteriaServlet"/>
         <!-- NAVBAR -->
        <%@include file="/WEB-INF/jspf/navbar.jspf" %>

        <!--Main Section -->
        <div class="container">
            <form class="well form-horizontal" method="post"  action="${pageContext.request.contextPath}/CriteriaSendInfoBussinessApprovedServlet" id="contact_form">
                <fieldset>
                    <!-- Form Name -->
                    <legend>Seleccione un criterio para ver su información</legend>
                    
                    <!-- Select basic for ID del criterio -->
                    <div class="form-group">
                        <label class="col-md-4 control-label">Criterio a aprobar</label>
                        <div class="col-md-4 selectContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <select name="id" class="form-control selectpicker" required>
                                    <option value="" >Seleccione el criterio</option>
                                    <c:forEach items="${criterios}" var="criterio">
                                        <option><c:out value="${criterio}"/></option>
                                    </c:forEach>
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
                            <button type="submit" class="btn btn-success" >Ver <span class="glyphicon glyphicon-send"></span></button>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>

        <div class="clearfix">
        </div>
    </body>
</html>
