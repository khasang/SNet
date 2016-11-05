
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Include HEADER-->
<jsp:include page="inc/header.jsp"/>
<!--PAGE CONTENT -->
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



