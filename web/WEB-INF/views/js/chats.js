function callChats(workingDir) {

    if (workingDir==null) workingDir = '';
    var service = workingDir + '/chat/all';
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState==4 && this.status==200) {
            printAllChats(this.responseText,workingDir);
        }
    };
    xhttp.open("GET", service, true);
    xhttp.send();

}

function printAllChats(jsonResponse,workingDir) {

    var chats = JSON.parse(jsonResponse);
    var out = "";
    var i;
    for (i = 0; i < chats.length; i++) {
        out += '<div>Chat: ' + chats[i].description + '<div class="col-sm-4 pull-right">' +
            '<a href="' + workingDir + '/messages?id=' + chats[i].id +
            '"><button class="btn btn-primary btn-xs">Read messages</button></a>' +
            '<button onclick="removeOne(' + chats[i].id + ',' + workingDir + ')" class="btn btn-danger btn-xs">Remove chat</button>' +
            '</div></div><hr>';
    }
    document.getElementById("listChats").innerHTML = out;

}

function removeOne(chatId, workingDir) {

    if (workingDir==null) workingDir = '';
    var service = workingDir + '/chat/remove';
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

    xhttp.open("POST",service, true);
    xhttp.send(chatId);

}
