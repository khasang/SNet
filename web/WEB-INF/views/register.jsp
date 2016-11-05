<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!-- Include HEADER-->
<jsp:include page="inc/header.jsp"/>
<!--SCRIPT FOR REGISTER -->
<script type="text/javascript">
    var service = '/register';
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

<!--PAGE CONTENT -->
<div class="page-content container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="login-wrapper">
                <div class="box">
                    <div class="content-wrap">
                        <h6>Sign Up</h6>
                        <form>
                            <input class="form-control" id="login" type="text" placeholder="Login">
                            <input class="form-control" id="email" type="text" placeholder="E-mail address">
                            <input class="form-control" id ="password" type="password" placeholder="Password">
                            <div class="action">
                                <a class="btn btn-primary signup"  onclick="RestPost()">Sign Up</a>
                            </div>
                        </form>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-body" id="response">
                        </div>
                    </div>
                </div>

                <div class="already">
                    <p>Have an account already?</p>
                    <a href="login">Login</a>
                </div>
            </div>

        </div>

    </div>
</div>


