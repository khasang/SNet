<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<html>
<head>
    <title>SNet Sign Up</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- styles -->
    <link href="css/styles.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->


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

</head>
<body class="login-bg">
<div class="header">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <!-- Logo -->
                <div class="logo">
                    <h1><a href="index.html">SNet</a></h1>
                </div>
            </div>
        </div>
    </div>
</div>

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





<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://code.jquery.com/jquery.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
<script src="js/custom.js"></script>
</body>
</html>



