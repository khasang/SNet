<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- Add Bootstrap Validation-->
<link rel="stylesheet" href="css/bootstrapValidator.css"/>
<script type="text/javascript" src="vendors/jquery/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="js/bootstrapValidator.js"></script>
<!-- Include HEADER-->
<jsp:include page="inc/header.jsp"/>
<!--PAGE CONTENT -->
<div class="page-content container">
    <div class="row">

        <div class="col-md-4 col-md-offset-4">
            <div class="content-wrap">
                <h2>Registration</h2>
                            <form:form id="registrationForm" class="form-horizontal" action="/register" method="post" modelAttribute="user" accept-charset="UTF-8" enctype="multipart/form-data">

                                    <c:if test="${not empty message}">
                                    <div class="form-group has-error">
                                        <span class="help-block"><i class="fa fa-warning"></i> <span class="glyphicon glyphicon-remove"></span> <c:out value="${message}"/></span>
                                    </div>
                                    </c:if>
                                    <div class="form-group">
                                        <input class="form-control" name="login" id="login" type="text" placeholder="Username"/>
                                    </div>
                                    <div class="form-group">
                                        <input class="form-control" name="mail" id="email" type="text" placeholder="E-mail address"/>
                                    </div>
                                    <div class="form-group">
                                        <input class="form-control" name="password" id ="password" type="password" placeholder="Password">
                                    </div>
                                    <div class="form-group">
                                        <input class="form-control" name="confirmPassword" type="password" placeholder="Confirm Password">
                                    </div>

                                    <div class="action">
                                        <button class="btn btn-primary signup" type="submit">Register</button>
                                    </div>
                            </form:form>
                <div class="already">
                    <p>Have an account already? <a href="/login"><button class="btn btn-success btn-xs" >Log In</button></a></p>
                </div>
            </div>
        </div>

    </div>
</div>


<script type="text/javascript">
    $(document).ready(function() {
        $('#registrationForm').bootstrapValidator({
            message: 'This value is not valid',
            fields: {
                login: {
                    message: 'The username is not valid',
                    validators: {
                        notEmpty: {
                            message: 'The username is required and can\'t be empty'
                        },
                        stringLength: {
                            min: 5,
                            max: 30,
                            message: 'The username must be more than 5 and less than 30 characters long'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_\.]+$/,
                            message: 'The username can only consist of alphabetical, number, dot and underscore'
                        }
                    }
                },
                mail: {
                    validators: {
                        notEmpty: {
                            message: 'The email address is required and can\'t be empty'
                        },
                        emailAddress: {
                            message: 'The input is not a valid email address'
                        }
                    }
                },
                password: {
                    validators: {
                        notEmpty: {
                            message: 'The password is required and can\'t be empty'
                        },
                        identical: {
                            field: 'confirmPassword',
                            message: 'The password and its confirm are not the same'
                        },
                        stringLength: {
                            min: 5,
                            max: 12,
                            message: 'The username must be more than 5 and less than 12 characters long'
                        }
                    }
                },
                confirmPassword: {
                    validators: {
                        notEmpty: {
                            message: 'The confirm password is required and can\'t be empty'
                        },
                        identical: {
                            field: 'password',
                            message: 'The password and its confirm are not the same'
                        },
                        stringLength: {
                            min: 5,
                            max: 12,
                            message: 'The username must be more than 5 and less than 12 characters long'
                        }
                    }
                }
            }
        });

    });
</script>

