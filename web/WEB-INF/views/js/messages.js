var currentChat = 0;

function callMessages(workingDir) {

    if (workingDir==null) workingDir = '';
    var service = workingDir + '/messages/list';
    var ChatObject = {
        'id': currentChat,
        'description' : ""
    };
    var json = JSON.stringify(ChatObject);
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState==4 && this.status==200) {
            printMessages(this.responseText);
        }
    };
    xhttp.open("POST", service, true);
    xhttp.send(json);

}

function printMessages(json) {

    var messages = JSON.parse(json);
    var out = "";
    var i;
    for (i = 0; i < messages.length; i++) {
        out += '<div class="content-box"><strong>' + messages[i].sender.login + ':</strong> '
            + messages[i].body + '</div>';
    }
    document.getElementById("listMessages").innerHTML = out;

}

function sendMessage(workingDir) {

    if (workingDir==null) workingDir = '';
    var msgBody = document.getElementById("inputedText").value;
    var directory = workingDir + '/messages/new';

    if (msgBody != "" && msgBody != " ") {
        var Message = {
           'id':0,
            'sender': {
                'id':0,
                'login':null,
                'activeUser':true
            },
            'chat':{
                'id': currentChat,
                "description":null
            },
            'body': msgBody,
            'stamp' : null
        };

        var json = JSON.stringify(Message);
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState==4 && this.status==200) {
                callMessages(workingDir);
            }
        };

        xhttp.open("POST",directory,true);
        xhttp.send(json);

        document.getElementById("inputedText").value = '';

    }
}
