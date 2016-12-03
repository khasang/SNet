<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="vendors/datatables/dataTables.bootstrap.css" rel="stylesheet" media="screen">
<!-- Include HEADER-->
<jsp:include page="inc/header.jsp"/>
<script src="${pageContext.request.contextPath}/js/friend.js"></script>
<!--PAGE CONTENT -->
<div class="page-content">
    <div class="row">
        <!-- Include leff menu-->
        <jsp:include page="inc/left_menu.jsp"/>
        <!--Main Content of page -->
        <div class="col-md-9">
            <div class="col-md-8 panel-warning">
                <div class="content-box-header panel-heading">
                    <div class="panel-title" style="padding-bottom: 0;"><b>Поиск друзей:</b></div>
                </div>
                <div class="col-md-12 panel-warning"
                     style="margin-top:10px;border:1px solid; border-color:#eee;background-color: #f5f5f5">
                    <body onload="callUsers('${pageContext.request.contextPath}')">
                    <div class="col-md-6 panel-warning">
                        <input class="form-control" id="searchLike">
                    </div>
                    <div class="col-md-6 panel-warning">
                        <button onclick="findUserLike('${pageContext.request.contextPath}')" type="button"
                                class="btn btn-primary">
                            Find
                        </button>
                    </div>
                    <div id="listUsers" class="panel-body"></div>
                    </body>
                </div>
            </div>
        </div>
    </div>
</div>
<br>
<br>
<br>
<!-- Include FOOTER-->
<jsp:include page="inc/footer.jsp"/>
