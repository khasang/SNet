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
        out += '<div>Chat: ' + chats[i].description + '<div class="col-sm-4 pull-right">' +
            '<a href="/messages?id=' + chats[i].id +
            '"><button class="btn btn-primary btn-xs">Read messages</button></a>' +
            '<button onclick="removeOne(' + chats[i].id + ')" class="btn btn-danger btn-xs">Remove chat</button>' +
            '</div></div><hr>';
    }
    document.getElementById("listChats").innerHTML = out;
}

function removeOne(chatId) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            if (this.responseText == "") {
                callChats();
            } else {
                alert(this.responseText);
            }

        }
    };

    xhttp.open("POST","/chat/remove", true);
    xhttp.send(chatId);
}
