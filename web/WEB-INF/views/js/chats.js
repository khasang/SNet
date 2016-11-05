function callChats() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState==4 && this.status==200) {
            printAllChats(this.responseText);
        }
    };
    xhttp.open("GET", "/chats/all", true);
    xhttp.send();
}

function printAllChats(jsonResponse) {
    var chats = JSON.parse(jsonResponse);
    var out = "";
    var i;
    for (i = 0; i < chats.length; i++) {
        out += '<div>Chat description: "' + chats[i].description + '"<a href="./messages?id='
            + chats[i].id + '"> Go</a></div>';
    }
    document.getElementById("listChats").innerHTML = out;
}
