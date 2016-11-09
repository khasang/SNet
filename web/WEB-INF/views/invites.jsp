<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Include HEADER-->
<jsp:include page="inc/header.jsp"/>
<!--PAGE CONTENT -->
<div class="page-content">
    <div class="row">
        <!-- Include leff menu-->
        <jsp:include page="inc/left_menu.jsp"/>
        <!--Main Content of page -->
        <div class="col-md-10">
            <div class="row">
                <c:if test="${empty friends}">
                    <p>There is no active Invites!</p>
                </c:if>
                <c:forEach items="${friends}" var="friend">
                    <div class="col-md-12">
                        <div class="content-box-large">
                            <div class="panel-heading">
                                <div class="panel-title"><c:out value ="${friend.login}"/></div>
                                <a href="/approve?friend=${friend.login}"><button class="btn btn-success btn-xs">Approve</button></a>
                            </div>
                            <div class="panel-body">
                                <p>City: <c:out value ="${friend.city}"/></p>
                                <br>
                                <p>Interests: <c:out value ="${friend.interests}"/></p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<!-- Include FOOTER-->
<jsp:include page="inc/footer.jsp"/>