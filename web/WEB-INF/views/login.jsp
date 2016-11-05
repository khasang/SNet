
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SNet</title>
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
                        <h6>Sign In</h6>
                        <form action="/j_spring_security_check" method="post">
                            <input id="j_username" name="j_username" class="form-control" type="text" placeholder="Login">
                            <input id="j_password" name="j_password" class="form-control" type="password" placeholder="Password">
                            <div class="action">
                                <button class="btn btn-primary signup" type="submit">Login</button>
                            </div>
                        </form>
                    </div>
                </div>

                <div class="already">
                    <p>Don't have an account yet?</p>
                    <a href="/register">Sign Up</a>
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



