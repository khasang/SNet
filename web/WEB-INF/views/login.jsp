
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Include HEADER-->
<jsp:include page="inc/header.jsp"/>
<!--PAGE CONTENT -->
<div class="page-content container">
    <div class="row">

        <div class="col-md-4 col-md-offset-4">
                    <div class="content-wrap">
                        <h2>Login</h2>
                        <form action="/j_spring_security_check" method="post">
                            <div class="form-group">
                                <input id="j_username" name="j_username" class="form-control" required="required" type="text" placeholder="Username">
                            </div>
                            <div class="form-group">
                                <input id="j_password" name="j_password" class="form-control" required="required" type="password" placeholder="Password">
                            </div>

                            <div class="action">
                                <button class="btn btn-primary signup" type="submit">Login</button>
                            </div>
                        </form>
                    </div>
                <div class="already">
                    <p>Don't have an account yet?  <a href="/register"><button class="btn btn-info btn-xs">Register</button></p>
                    </a>
                </div>
        </div>

    </div>
</div>



