<!-- Include HEADER-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
                        <form>
                            <fieldset>
                                <div class="form-group">
                                    <label>Логин:</label>
                                    <span class="form-control">${profile.login}</span>
                                </div>

                                <div class="form-group">
                                    <label>Имя:</label>
                                    <input class="form-control" placeholder="Text field" type="text"
                                           value="${profile.name}">
                                </div>
                                <div class="form-group">
                                    <label>Фамилия:</label>
                                    <input class="form-control" placeholder="Text field" type="text"
                                           value="${profile.surname}">
                                </div>
                                <div class="form-group">
                                    <label>Город:</label>
                                    <input class="form-control" placeholder="Text field" type="text"
                                           value="${profile.city}">
                                </div>
                                <div class="form-group">
                                    <label>Интересы:</label>
                                    <textarea class="form-control" placeholder="Textarea"
                                              rows="3">${profile.interests}</textarea>
                                </div>

                                <div class="form-group">
                                    <label>О себе:</label>
                                    <textarea class="form-control" placeholder="Textarea"
                                              rows="3">${profile.aboutMe}</textarea>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-2 control-label">File input</label>
                                    <div class="col-md-10">
                                        <form class="btn btn-default" action="/uploadFile" method="post"
                                              enctype="multipart/form-data">
                                            <input type="file" name="file">
                                            <input type="submit" value="обновить фото">
                                        </form>
                                    </div>
                                </div>
                                </br>
                                <button type="button" class="btn btn-primary" onclick="RestPut()">Сохранить обновления</button>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="inc/footer.jsp"/>
