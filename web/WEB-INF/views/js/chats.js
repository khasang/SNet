function callChats() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState==4 && this.status==200) {
            printAllChats(this.responseText);
        }
    };
    xhttp.open("GET", "/chat/all", true);
    xhttp.send();
}

function printAllChats(jsonResponse) {
    var chats = JSON.parse(jsonResponse);
    var out = "";
    var i;
    for (i = 0; i < chats.length; i++) {
        out += '<div class="content-box">Chat: ' + chats[i].description +
            '<br><a href="/messages?id='
            + chats[i].id +
            '"><button class="btn btn-default btn-xs">Read messages</button></a></div>';
    }
    document.getElementById("listChats").innerHTML = out;
}
