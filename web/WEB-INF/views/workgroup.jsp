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
                            <div class="panel-title" style="color: #8a6d3b;">
                                <h3>
                                    <button class="btn btn-info btn-xs"><c:out value="${workgroup.workgroupType}"/></button>
                                <c:out value="${workgroup.title}"/>
                                </h3>
                            </div>
                        </div>
                        <div class="content-box-large box-with-header">
                                <div class="panel-body" style="padding: 5px;"><b>Описание: </b><c:out value="${workgroup.description}"/></div>
                        </div>
                        <body onload="callNews('${workgroup.id}')">
                        <div class="content-box-header panel-heading">
                            <div class="panel-title"><b>Новости группы:</b></div>

                        <div id="listNews" class="panel-body"></div>
                        <br>
                        <br>
                        </div>
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
                                <div class="panel-body">
                                    <table class="table">
                                        <tr>
                                            <td>Mark</td>
                                        </tr>
                                        <tr>
                                            <td>Jacob</td>
                                        </tr>
                                        <tr>
                                            <td>Larry</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                            <!--- FORM add member in group ---->
                            <form>
                                <div class="form-group">
                                    <label>Select User</label>
                                    <select class="form-control" name="workgroupType" id="workgroupType">
                                        <option>User 1</option>
                                        <option>User 2</option>
                                        <option>User 3</option>
                                    </select>
                                </div>
                                <div class="pull-right">
                                    <button class="btn btn-primary" type="submit">Add</button>
                                </div>
                            </form>
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