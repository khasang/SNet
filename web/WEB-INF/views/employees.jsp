<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:include page="inc/header.jsp"/>

<script type="text/javascript">
    var service = '/api/employees';
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
            'id': $('#postId').val(),
            'name': $('#postName').val(),
            'age': $('#postAge').val(),
            'city': $('#postCity').val(),
            'salary': $('#postSalary').val(),

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
<div class="panel panel-default">
    <div class="panel-heading">
        REST API
    </div>
    <div class="panel-body">

        <table class="table">
            <thead>
            <tr>
                <th>Description</th>
                <th>Method</th>
                <th>Try</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>Get all employees</td>
                <td><code><strong>GET</strong> /api/employees/all</code></td>
                <td>
                    <button type="button" onclick="RestGet('all')">Try</button>
                </td>
            </tr>
            <tr>
                <td>Add Employee</td>
                <td><code><strong>POST</strong> /api/employees</code></td>
                <td>
                    <form class="form-inline">
                        id: <input type="text" id="postId">
                        <br>
                        name: <input type="text" id="postName">
                        age: <input type="text" id="postAge">
                        <br>
                        city: <input type="text" id="postCity">
                        salary: <input type="text" id="postSalary">
                        <br>
                        <button type="button" onclick="RestPost()">Add</button>
                    </form>
                </td>
            </tr>
            </tbody>
        </table>

    </div>
</div>
<div class="panel panel-default">
    <div class="panel-heading">
        <strong>RESPONSE</strong>
    </div>
    <div class="panel-body" id="response">
    </div>
</div>

<jsp:include page="inc/footer.jsp"/>