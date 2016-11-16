<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Include HEADER-->
<jsp:include page="inc/header.jsp"/>
<script src="js/workgroupUtils.js" type="text/javascript"></script>
<link href="vendors/datatables/dataTables.bootstrap.css" rel="stylesheet" media="screen">
<!--PAGE CONTENT -->
<div class="page-content">
    <div class="row">
        <!-- Include leff menu-->
        <jsp:include page="inc/left_menu.jsp"/>
        <!--Main Content of page -->
        <div class="col-md-9">
            <!--MAIN table of workgroup --->
            <div class="col-md-9">
                <div class="row">
                    <div class="col-md-12">
                        <div class="content-box-header">
                            <div class="panel-title" style="color: #8a6d3b;">
                                <h3>My Workgroup News:</h3>
                            </div>
                        </div>
                        <div class="content-box-header panel-heading">
                            <c:if test="${empty userWorkgroup}">
                                <p style="margin-left: 15px;margin-top: 10px;">Вы не состоите ни с одной группе!</p>
                            </c:if>
                            <c:forEach items="${userWorkgroup}" var="workgroups">
                                <div class="content-box-large">
                                    <h4><b><a href="/workgroup?id=${workgroups.id}"> <c:out
                                            value="${workgroups.title}"/> </a></b></h4>
                                    <p><c:out value="${workgroups.description}"/></p>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Workgroup Sidebar Widgets Column -->
            <div class="col-md-3">

                <!-- Blog Search Well -->
                <div class="well">
                    <h4>News Search</h4>
                    <div class="input-group">
                        <input type="text" class="form-control">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button"><i
                                    class="glyphicon glyphicon-search"></i></button>
                        </span>
                    </div>
                    <!-- /.input-group -->
                </div>


            </div>
        </div>
        <!-- /.row -->
    </div>
</div>
<!-- Include FOOTER-->
<jsp:include page="inc/footer.jsp"/>

