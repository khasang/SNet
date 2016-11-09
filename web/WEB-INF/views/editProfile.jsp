<!-- Include HEADER-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="inc/header.jsp"/>
<!--PAGE CONTENT -->
<div class="page-content">
    <div class="row">
        <!-- Include leff menu-->
        <jsp:include page="inc/left_menu.jsp"/>
        <!--Main Content of page -->
        <div class="col-md-10">
            <div class="col-md-8">
                <div class="content-box-header panel-heading">
                    <div class="content-box-large box-with-header">
                        <div >
                            <form:form method="post" modelAttribute="profile">
                                <fieldset>
                                    <spring:bind path="login">
                                        <form:hidden path="login"></form:hidden>
                                    </spring:bind>
                                    <spring:bind path="id">
                                        <form:hidden path="id"></form:hidden>
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
                                    <br>
                                    <button type="submit" class="btn btn-primary">Сохранить обновления</button>
                                </fieldset>
                            </form:form>
                        </div>
                            <div >
                                <form action="uploadFile" method="post"
                                      enctype="multipart/form-data">

                                    <input class="btn btn-default" id="postFile" type="file" name="file" value="Выбрать">
                                    <button type="submit" style="margin-top: 20px" class="btn btn-info">
                                        <i class="glyphicon glyphicon-refresh"></i>
                                        Обновить фото
                                    </button>
                                </form>
                            </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<jsp:include page="inc/footer.jsp"/>
