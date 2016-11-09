<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<jsp:include page="inc/header.jsp"/>
<%--<c:import var="profile" url="/use_profile/my" charEncoding="utf-8" />--%>


<div class="page-content">
    <div class="row">
        <!-- Include leff menu-->
        <jsp:include page="inc/left_menu.jsp"/>
        <!--Main Content of page -->
        <div class="col-md-10">
            <div class="col-md-8 panel-warning">
                <div class="content-box-header panel-heading">
                    <div class="panel-title" style="padding-bottom: 0;"><b>Профиль - ${profile.login}</b></div>
                </div>
                <div>
                    <div class="col-md-12 panel-warning" style="border:1px solid; border-color:#eee;background-color: #f5f5f5">
                        <div class="col-md-4 panel-warning">
                            <div><img style="padding: 15px;" width="200px" src="/ava/${profile.avatar}"/></div>
                            <div style="margin:10px;"><a href="upload">
                                <button class="btn btn-primary btn-sm">
                                    <i class="glyphicon glyphicon-pencil"></i>
                                    Редактировать
                                </button>
                            </a>
                            </div>
                            <%--<c:url value="images/avatars/anonimus.png" />"/>--%>

                            <div style="margin:10px;"><a href="">
                                <button class="btn btn-success btn-sm">
                                    <i class="glyphicon glyphicon-pencil"></i>
                                    Написать сообщение
                                </button>
                            </a>
                            </div>
                            <c:if test="${not empty profile.created}">
                                <div style="margin:10px;"><strong>На сайте с: </strong>
                                    <c:out value="${profile.created}"/></div>
                            </c:if>
                        </div>

                        <div class="col-md-8 panel-info">
                            <div class="panel-title" style="margin-bottom:10px;margin-top:20px;font-style: italic;font-weight:bold; color: #777777;">Основная информация:</div>
                            <c:if test="${not empty profile.name}">
                                <p><strong>Имя: </strong>
                                <c:out value="${profile.name}"/></p>
                            </c:if>
                            <c:if test="${not empty profile.surname}">
                                <p><strong>Фамилия: </strong>
                                <c:out value="${profile.surname}"/></p>
                            </c:if>

                            <div class="panel-title" style="margin-bottom:10px;font-weight:bold; font-style: italic;color: #777777;">Контактная информация:</div>

                            <c:if test="${not empty user.mail}">
                                <p><strong>E-mail: </strong>
                                <c:out value="${user.mail}"/></p>
                            </c:if>
                            <c:if test="${not empty profile.city}">
                                <p><strong>Город: </strong>
                                <c:out value="${profile.city}"/></p>
                            </c:if>
                            <div class="panel-title" style="margin-bottom:10px;font-weight:bold;font-style: italic;color: #777777;">Дополнительная информация:</div>

                            <c:if test="${not empty profile.interests}">
                                <p><strong>Интересы: </strong>
                                <c:out value="${profile.interests}"/></p>
                            </c:if>
                            <c:if test="${not empty profile.aboutMe}">
                                <p><strong>О себе: </strong>
                               <c:out value="${profile.aboutMe}"/></p>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
<jsp:include page="inc/footer.jsp"/>