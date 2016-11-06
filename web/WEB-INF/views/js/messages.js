var currentChat = 0;
function callMessages() {
    var service = "/messages/list";
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
