<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
    <!--Header-->
    <jsp:include page="inc/header.jsp"/>
    <div class="page-content container">
        <div class="row">
            <!--Left menu-->
            <jsp:include page="inc/left_menu.jsp"/>
            <!--Main content-->
            <div class="row">
                <div class="col-md-8">
                    <div class="content-box-large">
                        <div class="alert-danger">
                            <h4><strong>Error: </strong>${errorMsg}</h4>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
