<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- Include HEADER-->
<jsp:include page="inc/header.jsp"/>
<!--PAGE CONTENT -->
<div class="page-content">
    <div class="row">

        <div class="col-md-12">
            <div class="content-box-header">
                <div class="panel-title"><span class="glyphicon glyphicon-chevron-down"></span> Thank you for registration!</div>
            </div>
            <div class="content-box-large box-with-header">
                <c:if test="${not empty message}">
                                <p><c:out value="${message}"/></p>
                </c:if>
                <p>Now you have an account! You can : <a href="/login"><button class="btn btn-success btn-sm" >Log In</button></a></p>
                <br /><br />
            </div>
        </div>

    </div>
</div>
