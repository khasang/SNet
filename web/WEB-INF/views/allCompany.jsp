<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>All Company</title>
    <spring:url value="css/cssTable.css" var="mainCss" />
    <link href="${mainCss}" rel="stylesheet"  type="text/css"/>
</head>
<body>
<table class="simple-little-table" >
    <tr >
        <td>ID</td>
        <td>Name</td>
        <td>Address</td>
        <td>Age</td>
        <td>Salary</td>
    </tr>
    <c:forEach items="${allCompany}" var="company">
        <tr>
            <td><c:out value="${company.id}"/></td>
            <td><c:out value="${company.name}"/></td>
            <td><c:out value="${company.address}"/></td>
            <td><c:out value="${company.age}"/></td>
            <td><c:out value="${company.salary}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
