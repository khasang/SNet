<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:include page="inc/header.jsp"/>

<script type="text/javascript">
    var service = '/api/post';
    var RestGet = function (id) {
        $.ajax({
            type: 'GET',
            url: service + "/" + id,
            dataType: 'json',
            async: false,
            success: function (result) {
                $('#response').html(JSON.stringify(result));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $('#response').html(JSON.stringify(jqXHR));
            }
        });
    };
    var RestPost = function () {
        var JSONObject = {
            'title': $('#postTitle').val(),
            'context': $('#postContex').val(),

        };
        $.ajax({
            type: 'POST',
            url: service,
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(JSONObject),
            dataType: 'json',
            async: false,
            success: function (result) {
                $('#response').html(JSON.stringify(result));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $('#response').html(JSON.stringify(jqXHR));
            }
        });
    };

    var RestDelete = function () {
        $.ajax({
            type: 'DELETE',
            url: service + "/" + $('#deletePostId').val(),
            dataType: 'json',
            async: false,
            success: function (result) {
                $('#response').html(JSON.stringify(result));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $('#response').html(JSON.stringify(jqXHR));
            }
        });
    };
</script>
<div class="simple-little-table">
    <div><p><b>Добавить пост</b></p>
        <form class="form-inline">
            <p><span>Id:</span>
                <input type="text" value="" id="postTitle"></p>
            <p><textarea rows="10" cols="40" id="postContex"></textarea></p>
            <p>
                <button type="button" onclick="RestPost()">Опубликовать</button>
            </p>
        </form>
    </div>
    <div>
        <p><b>Удалить пост по id</b></p>
        <p><span>Id:</span>
            <input id="deletePostId" value="">
            <button type="button" onclick="RestDelete()">Удалить</button>
        </p>
    </div>
    <div>
        <p><b>Все посты</b></p>
        <span><button type="button" onclick="RestGet('all')">Показать</button></span>
    </div>
</div>
<br>
<div class="panel panel-default">
    <div class="panel-heading">
        <strong>Ответ</strong>
    </div>
    <div class="panel-body" id="response">
    </div>
</div>

<jsp:include page="inc/footer.jsp"/>