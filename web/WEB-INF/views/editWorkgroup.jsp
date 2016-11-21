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
                <div class="col-md-12">
                    <div class="content-box-large">
                        <div class="panel-heading">
                            <b><div class="panel-title">Edit Workgroup</div></b>
                        </div>
                        <div class="panel-body">
                            <form:form id="workgroupForm" action="${pageContext.request.contextPath}/updateWorkgroup/${workgroup.id}" method="post" modelAttribute="workgroup" accept-charset="UTF-8" enctype="multipart/form-data" >
                                <fieldset>
                                    <div class="form-group">
                                        <label>Title</label>
                                        <input class="form-control" name="title" id="title" placeholder="Title of workgroup" type="text" value="${workgroup.title}">
                                    </div>
                                    <div class="form-group">
                                        <label>Description</label>
                                        <textarea class="form-control" name="description" id="description" placeholder="Description of workgroup"  rows="10"><c:out value="${workgroup.description}"/></textarea>
                                    </div>
                                </fieldset>
                                <div>
                                    <button class="btn btn-primary signup" type="submit">Update</button>
                                </div>
                            </form:form>
                            <a href="${pageContext.request.contextPath}/allWorkgroups"><button class="btn btn-danger">Cancel</button></a>
                        </div>
                    </div>
                </div>
        </div>
    </div>
</div>
<!-- Include FOOTER-->
<jsp:include page="inc/footer.jsp"/>
