<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:include page="inc/header.jsp"/>

<script type="text/javascript">
    var service = '/users/register';
    var RestPost = function () {
        var JSONObject = {
            'login': $('#login').val(),
            'mail': $('#email').val(),
            'password': $('#password').val(),

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

</script>
<h3>Register User</h3>
<br>
<div class="panel panel-default">
    <form>
        <br>
        <p> Login : <input type="text" id="login"></p>
        <p> E-Mail : <input type="text" id ="email"></p>
        <p> Password : <input type="password" id="password"></p>
        <p><button type="button" onclick="RestPost()">Register</button></p>
    </form>

</div>

<div class="panel panel-default">
    <div class="panel-body" id="response">
    </div>
</div>



<jsp:include page="inc/footer.jsp"/>
