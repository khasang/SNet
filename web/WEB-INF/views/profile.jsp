
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:include page="inc/header.jsp"/>
<%--<c:import var="profile" url="/use_profile/my" charEncoding="utf-8" />--%>

<c:url value="/use_profile/uploadFile" var="fileUploadControllerURL" />
<div class="panel panel-default">
    <p><b>Мой профиль</b></p>
    <div class="panel-body" id="response"/>
    <div class="panel-body"></div>
</div>
<br>
<table width="100%" style="border-collapse:collapse;">
    <tr>
        <td width="150px" valign="top"><!--/Левая старана начало-->
            <table width="150px" style="border-collapse:collapse;">
                <tr>
                    <td class="avatare"><img src="http://deejaay.ru/watermark/myMeg.png" width="150px"></td>
                </tr>
                <!--/Меню-->
                <tr>
                    <td class="avatare">
                    <form class="mylivepage" action="${fileUploadControllerURL}" method="post"
                          enctype="multipart/form-data">
                        <input type="file" name="file">
                        <input type="submit" value="обновить фото">
                    </form>
                    </td>
                </tr>
                <!--/Меню конец-->
            </table>
        </td><!--/Левая старана конец-->
        <td valign="top"><!--/Правая старана начало-->
            <table style="border-collapse:collapse;margin-top:3px;">
                <tr>
                    <td class="leftiks">Логин:</td>
                    <td class="rightiks"></td>
                </tr>
                <tr>
                    <td class="leftiks">Имя:</td>
                    <td class="rightiks"></td>
                </tr>
                <tr>
                    <td class="leftiks">Фамилия:</td>
                    <td class="rightiks"></td>
                </tr>
                <tr>
                    <td class="leftiks">Возраст:</td>
                    <td class="rightiks"></td>
                </tr>
                <tr>
                    <td class="leftiks">Пол:</td>
                    <td class="rightiks"></td>
                </tr>
                <tr>
                    <td class="leftiks">Место проживания:</td>
                    <td class="rightiks"></td>
                </tr>

                <tr>
                    <td class="leftiks">Дата Рождения:</td>
                    <td class="rightiks"></td>
                </tr>
                <tr>
                    <td class="leftiks">Зарегистрирован:</td>
                    <td class="rightiks"></td>
                </tr>
                <tr>
                    <td class="leftiks">Последний визит:</td>
                    <td class="rightiks"></td>
                </tr>
                <tr>
                    <td class="promij" colspan="2"></td>
                </tr>
                <tr>
                    <td class="leftiks">Интересы:</td>
                    <td class="rightiks"></td>
                </tr>
                <tr>
                    <td class="leftiks">О себе:</td>
                    <td class="rightiks"></td>
                </tr>
                <tr>
                    <td class="promij" colspan="2"></td>
                </tr>
                <tr>
                    <td class="leftiks">Сайт:</td>
                    <td class="rightiks"></td>
                </tr>
                <tr>
                    <td class="leftiks">E-mail:</td>
                    <td class="rightiks"></td>
                </tr>


            </table>
        </td>
    </tr>
</table>

<br>
<div class="panel panel-default">
    <p><b>Это для редактирования</b></p>
</div>

<form style="border-collapse:collapse;margin-top:3px;">
    <table style="border-collapse:collapse;margin-top:3px;">
        <tr>
            <td class="leftiks">id:</td>
            <td class="rightiks2"><input type="text" id="putID" value=""></td>
        </tr>

        <tr>
            <td class="leftiks">login:</td>
            <td class="rightiks2"><input type="text" id="putLogin" value=""></td>
        </tr>
        <tr>
            <td class="leftiks">Имя:</td>
            <td class="rightiks2"><input type="text" id="putName" value=""></td>
        </tr>
        <tr>
            <td class="leftiks">Фамилия:</td>
            <td class="rightiks2"><input type="text" id="putSurname" value=""></td>
        </tr>

        <tr>
            <td class="leftiks">Город:</td>
            <td class="rightiks2"><input type="text" id="putCity" value=""></td>
        </tr>

        <tr>
            <td class="leftiks">Интересы:</td>
            <td class="rightiks2"><textarea rows="10" cols="40" id="putInterests"></textarea></td>
        </tr>
        <tr>
            <td class="leftiks">О себе:</td>
            <td class="rightiks2"><textarea rows="10" cols="40" id="putAboutMe"></textarea></td>
        </tr>
    </table>
    <button type="button" onclick="RestPut()">Обновить</button>
    <span id="responsePut"/>
</form>
<br>
<br>


<style type="text/css">
    .activite_wall{display:block;background:#f9f9f9;padding:4px;width:560px;}
    .activite_wall:hover{background:#F9FCFF;}
    .avatare{
        padding:3px;
        border:1px solid #d2d2d2;
    }
    .leftiks{
        color:#666666;
        width:130px;
        padding:5px;
        background:#f5f5f5;
        border-top:1px solid #d2d2d2;
        border-bottom:1px solid #d2d2d2;
        border-left:1px solid #d2d2d2;
        border-right:1px dotted #d2d2d2;
    }
    .rightiks{
        width:420px;
        padding:5px;
        background:#f9f9f9;
        border-top:1px solid #d2d2d2;
        border-bottom:1px solid #d2d2d2;
        border-left:1px dotted #d2d2d2;
        border-right:1px solid #d2d2d2;
    }

    .rightiks2{
        width:200px;
        padding:5px;
        background:#f9f9f9;
        border-top:1px solid #d2d2d2;
        border-bottom:1px solid #d2d2d2;
        border-left:1px dotted #d2d2d2;
        border-right:1px solid #d2d2d2;
    }
    .promij{
        height:20px;
        background:url('http://deejaay.ru/shb_0_1/PPL/back_pers.png')no-repeat;
        border-left:1px solid #d2d2d2;
        border-right:1px solid #d2d2d2;
    }
</style>


<script type="text/javascript">
    var service = '/use_profile';
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

    var RestPut = function () {
        var JSONObject = {
            'id': $('#putID').val(),
            'login': $('#putLogin').val(),
            'name': $('#putName').val(),
            'surname': $('#putSurname').val(),
            'city': $('#putCity').val(),
            'interests': $('#putInterests').val(),
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
                $('#responsePut').html("Данные успешно обновлены");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $('#responsePut').html(JSON.stringify(jqXHR));
            }
        });
    };
    RestGet('my');
</script>

<jsp:include page="inc/footer.jsp"/>