<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
    <title>SNet</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- styles -->
    <link href="css/styles.css" rel="stylesheet">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="header">
    <div class="container">
        <div class="row">
            <div class="col-md-5">
                <!-- Logo -->
                <div class="logo">
                    <h1><a href="/"><i class="glyphicon glyphicon-globe"></i> SNet</a></h1>
                </div>
            </div>
            <div class="col-md-6 pull-right">
                <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')" var="isUSer"/>
                <ul class="nav navbar-nav navbar-right">
                    <c:if test="${not isUSer}">
                        <li style="padding-top: 15px; padding-bottom: 15px; margin-right:5px; color:#c12e2a">
                            <c:if test="${empty param.error}">
                                You are not Loged In
                            </c:if>
                        </li>
                        <li> <a style="color: white; background-color: #2c3742" href="${pageContext.request.contextPath}/login"> Log In</a> </li>
                    </c:if>
                    <c:if test="${isUSer}">
                        <li style="padding-top: 15px; padding-bottom: 15px; color: lightskyblue">
                            You logged as: <b><sec:authentication property="principal.username"/></b>
                        </li>
                        <li> <a style="color: whitesmoke;background-color: #2c3742" href="${pageContext.request.contextPath}/logout">Logout</a> </li>
                    </c:if>
                </ul>
            </div>
        </div>
    </div>
</div>