<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:include page="inc/header.jsp"/>

<script type="text/javascript">
    var service = '/api/question';
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
            'question': $('#postQuestion').val(),
            'answer1': $('#postAnswer1').val(),
            'answer2': $('#postAnswer2').val(),
            'answer3': $('#postAnswer3').val(),
            'answer4': $('#postAnswer4').val(),
            'correectAnswers': $('#postCorrectAnswer').val(),

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
    var RestPut = function () {
        var JSONObject = {
            'id': $('#putId').val(),
            'answer1': $('#putAnswer1').val(),
            'answer2': $('#putAnswer2').val(),
            'answer3': $('#putAnswer3').val(),
            'answer4': $('#putAnswer4').val(),
            'correectAnswers': $('#putCorrectAnswer').val(),
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
    var RestDelete = function () {
        $.ajax({
            type: 'DELETE',
            url: service + "/" + $('#deleteQuestionId').val(),
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
                <th>Desctiption</th>
                <th>Method</th>
                <th>Try</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>Get all question</td>
                <td><code><strong>GET</strong> /api/question/all</code></td>
                <td>
                    <button type="button" onclick="RestGet('all')">Try</button>
                </td>
            </tr>
            <tr>
                <td>Get question by id</td>
                <td><code><strong>GET</strong> /api/question/{id}</code></td>
                <td>
                    Id: <input id="getQuestionID" value="3">
                    <button type="button" onclick="RestGet($('#getQuestionID').val())">Try</button>
                </td>
            </tr>
            <tr>
                <td>Add question</td>
                <td><code><strong>POST</strong> /api/question</code></td>
                <td>
                    <form class="form-inline">
                        question: <input type="text" value="question" id="postQuestion">
                        <br>
                        answer1: <input type="text" id="postAnswer1" value="answer1">
                        answer2: <input type="text" id="postAnswer2" value="answer2">
                        <br>
                        answer3: <input type="text" id="postAnswer3" value="answer3">
                        answer4: <input type="text" id="postAnswer4" value="answer4">
                        <br>
                        CorrectAnswers: <input type="text" id="postCorrectAnswer" value="answer1">
                        <button type="button" onclick="RestPost()">Try</button>
                    </form>
                </td>
            </tr>
            <tr>
                <td>Update question</td>
                <td><code><strong>PUT</strong> /api/question</code></td>
                <td>
                    <form class="form-inline">
                        Id: <input type="text" value="3" id="putId">
                        <br>
                        answer1: <input type="text" id="putAnswer1" value="another answer1">
                        answer2: <input type="text" id="putAnswer2" value="another answer2">
                        <br>
                        answer3: <input type="text" id="putAnswer3" value="another answer3">
                        answer4: <input type="text" id="putAnswer4" value="another answer4">
                        <br>
                        CorrectAnswers: <input type="text" id="putCorrectAnswer" value="another answer1">
                        <button type="button" onclick="RestPut()">Try</button>
                    </form>
                </td>
            </tr>
            <tr>
                <td>Delete question by id</td>
                <td><code><strong>DELETE</strong> /api/question/{id}</code></td>
                <td>
                    Id: <input id="deleteQuestionId" value="4">
                    <button type="button" onclick="RestDelete()">Try</button>
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