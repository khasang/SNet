<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                    <div class="panel-title">Departmets,Units and Groups</div>
                </div>
                <div class="panel-body">
                <p>
                       <button class="btn btn-success"><i class="glyphicon glyphicon-plus-sign"></i> Add</button>
                    </p>
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
                                    <a href="/workgroupEdit/${workgroups.id}"><button class="btn btn-primary"><i class="glyphicon glyphicon-pencil"></i> Edit</button></a>
                                    <a href="/workgroupDelete/${workgroups.id}"><button class="btn btn-danger"><i class="glyphicon glyphicon-remove"></i> Delete</button></a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
     </div>
</div>
<!-- Include FOOTER-->
<jsp:include page="inc/footer.jsp"/>