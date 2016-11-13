<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Include HEADER-->
<jsp:include page="inc/header.jsp"/>
<script src="./js/workgroupNews.js" type="text/javascript"></script>
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

                            <div class="panel-title">
                                <h3>
                                    <button class="btn btn-info btn-xs"><c:out value="${workgroup.workgroupType}"/></button>
                                <c:out value="${workgroup.title}"/>
                                </h3>
                            </div>
                        </div>


                        <div class="content-box-large box-with-header">
                            <b><c:out value="${workgroup.description}"/></b>
                            <br /><br />
                        </div>

                        <body onload="callNews('${workgroup.id}')">
                        <div class="panel-heading"> News</div>
                        <div id="listNews" class="panel-body"></div>
                        <br>
                        <br>
                        </body>

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
                            <button class="btn btn-default" type="button"><i class="glyphicon glyphicon-search"></i></button>
                        </span>
                    </div>
                    <!-- /.input-group -->
                </div>

                <!-- Members -->
                <div class="well">
                    <h4>Workgroup Members</h4>
                    <div class="row">
                        <div class="col-lg-12">

                            <div class="content-box">
                            Member 1
                            </div>

                            <div class="content-box">
                                Member 2
                            </div>
                        </div>

                    </div>
                    <!-- /.row -->
                </div>



            </div>

        </div>
        <!-- /.row -->
    </div>
</div>
<!-- Include FOOTER-->
<jsp:include page="inc/footer.jsp"/>