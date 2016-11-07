<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--Including header-->
<jsp:include page="inc/header.jsp"/>
<html>
<head>
    <title>Your messages</title>
    <script src="./js/messages.js"></script>
    <script type="text/javascript">
        currentChat=${chat_id};
    </script>
</head>
<body onload="callMessages()">
    <div class="page-content container">
        <div class="row">
            <!--Left menu-->
            <jsp:include page="inc/left_menu.jsp"/>
            <!--Main content-->
            <div class="row">
                <div class = "col-md-8">
                    <div class="content-box-large">
                        <div class="panel-heading">
                            <div class="panel-title">This is your messages</div>
                        </div>
                        <div id="listMessages" class="panel-body">
                            <!-- There messages loads -->
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading">Send new message</div>
                            <div class="panel-body">
                                <form>
                                    <div class="form-group">
                                        <label for="inputedText">Type text of new message below:</label>
                                        <textarea class="form-control" rows="5" id="inputedText"></textarea>
                                        <button onclick="alert($('#inputedText').val())" type="button" class="btn btn-primary">
                                            Send
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
