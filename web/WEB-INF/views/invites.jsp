<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Include HEADER-->
<jsp:include page="inc/header.jsp"/>
<!--PAGE CONTENT -->
<div class="page-content">
    <div class="row">
        <!-- Include leff menu-->
        <jsp:include page="inc/left_menu.jsp"/>
        <!--Main Content of page -->
        <div class="col-md-9">
            <div class="col-md-8 panel-warning">
                <div class="content-box-header panel-heading">
                    <div class="panel-title" style="padding-bottom: 0;"><b>Заявки в друзья:</b></div>
                </div>
                <div class="col-md-12 panel-warning" style="border:1px solid; border-color:#eee;background-color: #f5f5f5">
                    <c:if test="${empty friends}">
                        <p>There is no active Invites! <a href="searchFriends"><button class="btn btn-warning btn-xs">Search Friends</button></a></p>
                    </c:if>
                    <c:forEach items="${friends}" var="friend">
                        <div class="col-md-12" style="margin-top: 15px;margin-bottom: 15px;background-color: #ffffff;">

                            <div class="col-md-3 panel-warning">
                                <div><img style="padding: 15px;" width="150px" src="/ava/${friend.avatar}"/></div>
                            </div>
                            <div class="col-md-9 panel-info">
                                <div >
                                    <div class="panel-title" style="margin-bottom:10px;margin-top:20px;
                            font-style: italic;font-weight:bold; color: #777777;">
                                        <a href="user?userLogin=${friend.login}">
                                            <c:out value ="${friend.login}"/>
                                        </a>
                                    </div>
                                </div>
                                <div style="padding: 5px;">
                                    <p><strong>Город: </strong> <c:out value ="${friend.city}"/></p>
                                </div>
                                <div style="margin:5px;">
                                    <a href="/approve?friend=${friend.login}"><button class="btn btn-success btn-xs">Approve</button></a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

    </div>
</div>
<!-- Include FOOTER-->
<jsp:include page="inc/footer.jsp"/>