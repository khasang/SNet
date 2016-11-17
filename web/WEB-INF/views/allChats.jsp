<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!--Header-->
<jsp:include page="inc/header.jsp"/>
<html>
<head>
    <title>Your chats</title>
    <script src="${pageContext.request.contextPath}/js/chats.js"></script>
</head>
<body onload="callChats(&apos;${pageContext.request.contextPath}&apos;)">
    <div class="page-content">
        <div class="row">
            <!--Left menu-->
            <jsp:include page="inc/left_menu.jsp"/>
            <!--Main content-->
            <div class="row">
                <div class="col-md-8">
                    <div class="content-box-large">
                        <div class="panel-heading">
                            <div class="panel-title">There all your chats</div>
                        </div>
                        <div id="listChats" class="panel-body">
                            <!-- Chats loads there-->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <footer>
        <jsp:include page="inc/footer.jsp"/>
    </footer>
</body>
</html>
