<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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
                    <div class="panel-title"><b>Профиль - ${profile.login}</b></div>
                </div>
                <div style = "background-color: #0fa6bc">
                    <div class="col-md-4 panel-warning">
                    <div><img src="<c:url value="images/avatars/anonimus.png" />"/></div>
                    <div style="margin:10px;"><a href="upload">
                        <button class="btn btn-primary btn-sm">
                            <i class="glyphicon glyphicon-pencil"></i>
                            Редактировать
                        </button>
                    </a>
                    </div>

                    <div style="margin:10px;"><a href="">
                        <button class="btn btn-success btn-sm">
                            <i class="glyphicon glyphicon-pencil"></i>
                            Написать сообщение
                        </button>
                    </a>
                    </div>
                </div>

                    <div class="col-md-8 panel-info">
                        <div class="panel-title">Основная информация:</div>
                        <br>
                        <c:if test="${not empty profile.name}">
                            <span><strong>Имя: </strong></span>
                            <span><c:out value="${profile.name}"/></span>
                        </c:if>
                        <c:if test="${not empty profile.surname}">
                            <span><strong>Фамилия: </strong></span>
                            <span><c:out value="${profile.surname}"/></span>
                        </c:if>

                        <div class="panel-title">Контактная информация:</div>
                        <br>
                        <c:if test="${not empty profile.city}">
                            <span><strong>Город: </strong></span>
                            <span><c:out value="${profile.city}"/></span>
                        </c:if>
                        <div class="panel-title">Дополнительная информация:</div>
                        <br>
                        <c:if test="${not empty profile.interests}">
                            <span><strong>Интересы: </strong></span>
                            <span><c:out value="${profile.interests}"/></span>
                        </c:if>
                        <c:if test="${not empty profile.aboutMe}">
                            <span><strong>О себе: </strong></span>
                            <span><c:out value="${profile.aboutMe}"/></span>
                        </c:if>
                    </div>
                </div>
                </div>
            </div>
        </div>
    </div>
</br>
</br>
</br>
<jsp:include page="inc/footer.jsp"/>