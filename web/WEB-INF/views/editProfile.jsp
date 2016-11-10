<!-- Include HEADER-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="inc/header.jsp"/>
<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="./js/profile.js"></script>
<!--PAGE CONTENT -->
<div class="page-content">
    <div class="row">
        <!-- Include leff menu-->
        <jsp:include page="inc/left_menu.jsp"/>
        <!--Main Content of page -->
        <div class="col-md-10">
            <div class="col-md-8 panel-warning" >
                <div class="content-box-header panel-heading">
                    <div class="panel-title"><b>Редактировать профиль</b></div>
                    </div>
                <div class="content-box-header ">

                        <div style="margin:20px; width: 40% " >
                            <form:form method="post"  modelAttribute="profile">
                                <fieldset>
                                    <spring:bind path="login">
                                        <form:hidden path="login"></form:hidden>
                                    </spring:bind>
                                    <spring:bind path="login">
                                        <div class="form-group">
                                            <label>Логин:</label>
                                            <span class="form-control">${profile.login}</span>
                                        </div>
                                    </spring:bind>
                                    <spring:bind path="name">
                                        <div class="form-group">
                                            <label>Имя:</label>
                                            <form:input class="form-control" type="text" placeholder=""
                                                        path="name"></form:input>
                                        </div>
                                    </spring:bind>

                                    <spring:bind path="surname">
                                        <div class="form-group">
                                            <label>Фамилия:</label>
                                            <form:input class="form-control" placeholder="" type="text"
                                                        path="surname"></form:input>
                                        </div>
                                    </spring:bind>
                                    <spring:bind path="city">
                                        <div class="form-group">
                                            <label>Город:</label>
                                            <form:input class="form-control" placeholder="" type="text"
                                                        path="city"></form:input>
                                        </div>
                                    </spring:bind>
                                    <spring:bind path="interests">
                                        <div class="form-group">
                                            <label>Интересы:</label>
                                            <form:textarea path="interests" class="form-control"
                                                           placeholder="Мои увлечения"
                                                           rows="3"></form:textarea>
                                        </div>
                                    </spring:bind>
                                    <spring:bind path="aboutMe">
                                        <div class="form-group">
                                            <label>О себе:</label>
                                            <form:textarea path="aboutMe" class="form-control"
                                                           placeholder="Расскажите о себе"
                                                           rows="3"></form:textarea>
                                        </div>
                                    </spring:bind>
                                    <button type="submit" class="btn btn-primary">Сохранить обновления</button>
                                </fieldset>
                            </form:form>
                        </div>
                            <div style="margin-bottom: 30px" >
                                <hr>
                                  <form id="upload-file-form">
                                      <label for="upload-file-input">Обновить аватар:</label>
                                      <input id="upload-file-input" type="file" name="uploadfile" accept="*" />
                                      <span id="upload-file-message"></span>
                                  </form>
                            </div>
                    </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="inc/footer.jsp"/>
