
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:include page="inc/header.jsp"/>


<div class="simple-little-table">

    <div>
        <p><b>Мой профиль</b></p>
        <span><button type="button" onclick="RestGet('my')">Обновить</button></span>
    </div>
</div>
<br>
<div class="panel panel-default">
    <div class="panel-heading">
    </div>
        <div class="panel-body" id="result">

    </div>
</div>


   <div>
        <form class="">
            Имя:<input type="text" id="putName" value="">
            <br>
            Фамилия: <input type="text" id="putSurname" value="">
            <br>
            Город: <input type="text" id="putCity" value="">
            <p> Увлечения:<textarea rows="10" cols="40" id="interests"></textarea></p>
            <br>
            <p> О себе:<textarea rows="10" cols="40" id="aboutMe"></textarea></p>
            <button type="button" onclick="RestPut()">Try</button>
        </form>
    <div>



<script type="text/javascript">
    var service = '/use_profile';
    var RestGet = function (id) {
        $.ajax({
            type: 'GET',
            url: service + "/" + id,
            dataType: 'json',
            async: false,
            success: function (result) {
                $('#result').html(JSON.stringify(result));

            },
            error: function (jqXHR, textStatus, errorThrown) {
                $('#response').html(JSON.stringify(jqXHR));
            }
        });
    };

    var RestPut = function () {
        var JSONObject = {

            'name': $('#putName').val(),
            'surname': $('#putSurname').val(),
            'city': $('#putCity').val(),
            'interests': $('#putInterest').val(),
            'aboutMe': $('#putAboutMe').val(),

        };
        $.ajax({
            type: 'PUT',
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

    RestGet('my');
</script>



<jsp:include page="inc/footer.jsp"/>