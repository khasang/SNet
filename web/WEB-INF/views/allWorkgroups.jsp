<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- Include HEADER-->
<jsp:include page="inc/header.jsp"/>
<link href="vendors/datatables/dataTables.bootstrap.css" rel="stylesheet" media="screen">
<!--PAGE CONTENT -->
<div class="page-content">
    <div class="row">
        <!-- Include leff menu-->
        <jsp:include page="inc/left_menu.jsp"/>
        <!--Main Content of page -->
        <div class="col-md-9">
            <div class="content-box-large">
                <div class="panel-heading">
                    <b><div class="panel-title">Departmets,Units and Groups</div></b>
                </div>
                <!--- INFO about operations ---->
                <c:if test="${ param.delMessage != null}">
                    <div class="alert alert-success">
                        <div class="panel-title"><span class="glyphicon glyphicon-chevron-down"></span> <b><c:out value="${param.delMessage}"/></b></div>
                    </div>
                </c:if>
                <c:if test="${ param.updateMessage != null}">
                    <div class="alert alert-success">
                        <div class="panel-title"><span class="glyphicon glyphicon-chevron-down"></span> <b><c:out value="${param.updateMessage}"/></b></div>
                    </div>
                </c:if>
                <c:if test="${ param.deleteMessage != null}">
                    <div class="alert alert-danger">
                        <div class="panel-title"><span class="glyphicon glyphicon-remove"></span> <b><c:out value="${param.deleteMessage}"/></b></div>
                    </div>
                </c:if>
                <!--- Table of workgroups --->
                <div class="panel-body">
                    <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered" id="example">
                        <thead>
                        <tr>
                            <th>id</th>
                            <th>Type</th>
                            <th>Title</th>
                            <th>Head workgroup</th>
                            <th>Description</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${workgroups}" var="workgroups">
                            <tr class="odd gradeX">
                                <td><c:out value="${workgroups.id}"/></td>
                                <td><c:out value="${workgroups.workgroupType}"/></td>
                                <td><c:out value="${workgroups.title}"/></td>
                                <td class="center"> <c:out value="${workgroups.headWorkgroupId}"/></td>
                                <td class="center"><c:out value="${workgroups.description}"/></td>
                                <td class="table-striped">
                                    <a href="/workgroup?id=${workgroups.id}"><button class="btn btn-default"><i class="glyphicon glyphicon-eye-open"></i> View</button></a>
                                    <a href="/workgroupEdit?id=${workgroups.id}"><button class="btn btn-primary"><i class="glyphicon glyphicon-pencil"></i> Edit</button></a>
                                    <a href="/workgroupDelete?id=${workgroups.id}"><button class="btn btn-danger"><i class="glyphicon glyphicon-remove"></i> Delete</button></a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                <!--Form to Add new Work group--->
                    <br>
                    <br>
                    <hr>
                    <div class="col-md-12">
                        <div class="content-box-large">
                            <c:if test="${ param.addMessage != null}">
                                <div class="alert alert-success">
                                        <div class="panel-title"><span class="glyphicon glyphicon-chevron-down"></span> <b><c:out value="${param.addMessage}"/></b></div>
                                    </div>
                            </c:if>
                            <div class="panel-heading">
                                <b><div class="panel-title">Add new Workgroup</div></b>
                            </div>
                            <div class="panel-body">
                                <form:form id="workgroupForm" action="/addNewWorkgroup" method="post" modelAttribute="workgroup" accept-charset="UTF-8" enctype="multipart/form-data" >
                                    <fieldset>
                                        <div class="form-group">
                                            <label>Title</label>
                                            <input class="form-control" name="title" id="title" placeholder="Title of workgroup" type="text">
                                        </div>
                                        <div class="form-group">
                                            <label>Type</label>
                                            <select class="form-control" name="workgroupType" id="workgroupType">
                                                <c:forEach items="${types}" var="type">
                                                   <option value="${type}"> <c:out value="${type}"/></option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>Head workgroup</label>
                                            <select class="form-control" name="headWorkgroupId" id="headWorkgroupId">
                                                <option value="0"> NONE</option>
                                                <c:forEach items="${workgroups}" var="wg">
                                                    <option value="${wg.id}"> <c:out value="${wg.title}"/></option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>Description</label>
                                            <textarea class="form-control" name="description" id="description" placeholder="Description of workgroup" rows="10"></textarea>
                                        </div>
                                    </fieldset>
                                    <div>
                                        <button class="btn btn-primary signup" type="submit">Add</button>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
            </div>
            </div>
        </div>
     </div>
</div>

<!-- Include FOOTER-->
<jsp:include page="inc/footer.jsp"/>