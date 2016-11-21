<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Include HEADER-->
<jsp:include page="inc/header.jsp"/>
<script src="${pageContext.request.contextPath}/js/workgroupUtils.js" type="text/javascript"></script>
<link href="vendors/datatables/dataTables.bootstrap.css" rel="stylesheet" media="screen">
<sec:authorize access="hasAnyRole('ROLE_ADMIN')" var="isUSer"/>
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
                                    <button class="btn btn-info btn-xs"><c:out
                                            value="${workgroup.workgroupType}"/></button>
                                    <c:out value="${workgroup.title}"/>
                                </h3>
                            </div>
                        </div>
                        <div class="content-box-large box-with-header">
                            <div class="panel-body" style="padding: 5px;"><b>Описание: </b><c:out
                                    value="${workgroup.description}"/></div>
                        </div>

                        <body onload="LoadNewsAndMembers('${pageContext.request.contextPath}/news/${workgroup.id}','${pageContext.request.contextPath}/members/${workgroup.id}','${pageContext.request.contextPath}/notMembers/${workgroup.id}')">
                        <div>
                            <c:if test="${isUSer}">
                                <div class="col-md-8 panel-info" style="margin-bottom: 20px;">
                                    <div class="content-box-header panel-heading">
                                        <div class="panel-title"><b>Добавить новость: </b></div>
                                    </div>
                                    <div class="content-box-header  ">
                                        <div>
                                            <label>Заголовок:</label>
                                            <input class="form-control" id="workNewsTitle" type="text"/>
                                        </div>
                                        <div class="form-group">
                                            <label>Содержание:</label>
                                            <textarea class="form-control" id="workNewsDescr" type="text"></textarea>
                                        </div>
                                        <button style="margin-bottom: 15px;" type="submit" class="btn btn-primary"
                                                onclick=" addWorkgroupNews('${pageContext.request.contextPath}/news/new',${workgroup.id},'${pageContext.request.contextPath}/news/${workgroup.id}')">Добавить
                                        </button>

                                    </div>
                                </div>
                            </c:if>
                            <div class="col-md-12">
                                <div class="content-box-header panel-heading">
                                    <div class="panel-title"><b>Новости группы:</b></div>
                                    <div id="listNews" class="panel-body"></div>
                                    <br>
                                    <br></div>
                            </div>
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
                                    <div id="listMembers" class="panel-body"></div>
                                </div>
                            </div>
                            <c:if test="${isUSer}">
                                <!--- FORM add member in group ---->
                                <form>
                                    <div class="form-group">
                                        <label>Add users to workgroup</label>
                                        <select class="form-control" name="workgroupType" id="listNotMembers">
                                        </select>
                                    </div>
                                </form>
                                <div class="pull-right">
                                    <button class="btn btn-primary" onclick="addUserToWorkgroup('${pageContext.request.contextPath}/members/new',${workgroup.id},'${pageContext.request.contextPath}/members/${workgroup.id}','${pageContext.request.contextPath}/notMembers/${workgroup.id}')">
                                        Add
                                    </button>
                                </div>
                            </c:if>
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