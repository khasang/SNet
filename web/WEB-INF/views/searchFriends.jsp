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
                    <div class="panel-title" style="padding-bottom: 0;"><b>Поиск друзей:</b></div>
                </div>
                <div class="col-md-12 panel-warning" style="border:1px solid; border-color:#eee;background-color: #f5f5f5">
                    <c:if test="${empty friends}">
                        <p>You have no Friends! :(</p>
                    </c:if>
                    <c:forEach items="${friends}" var="friend">
                        <div class="col-md-12" style="margin-top: 15px;margin-bottom: 15px;background-color: #ffffff;">

                            <div class="col-md-3 panel-warning">
                                <div><img style="padding: 15px;" width="150px" src="/ava/${friend.key.avatar}"/></div>
                            </div>
                            <div class="col-md-9 panel-info">


                                <div >
                                    <div class="panel-title" style="margin-bottom:10px;margin-top:20px;
                            font-style: italic;font-weight:bold; color: #777777;">
                                        <a href="${pageContext.request.contextPath}/user?userLogin=${friend.key.login}">
                                            <c:out value ="${friend.key.login}"/>
                                        </a>
                                    </div>
                                </div>
                                <div style="padding: 5px;">
                                    <p><strong>Город: </strong> <c:out value ="${friend.key.city}"/></p>
                                </div>
                                <div style="margin:5px;">
                                    <c:if test="${friend.value.equals('IN_FRIENDS')}">
                                       <p>This person is already in your friends</p>
                                    </c:if>

                                    <c:if test="${friend.value.equals('NOT_A_FRIEND')}">
                                        <p>You can invite:</p>
                                        <a href="${pageContext.request.contextPath}/addFriend?friend=${friend.key.login}"><button class="btn btn-success btn-xs">Invite</button></a>
                                    </c:if>

                                    <c:if test="${friend.value.equals('INVITE_SENDED')}">
                                        <p>You already send an invite</p>
                                    </c:if>

                                    <c:if test="${friend.value.equals('INVITE_RECEIVED')}">
                                        <p>You have an invite from this Person</p>
                                        <a href="${pageContext.request.contextPath}/approve?friend=${friend.key.login}"><button class="btn btn-success btn-xs">Approve</button></a>
                                    </c:if>

                                </div>
                            </div>
                        </div>
                    </c:forEach>
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