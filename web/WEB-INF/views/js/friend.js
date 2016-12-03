function findUserLike(workingDir) {
    if (workingDir == null) workingDir = '';
    var searchBody = document.getElementById("searchLike").value;;
    var service2 = workingDir + '/searchFriendsLike/' + searchBody;
    if (searchBody != "" && searchBody != " ") {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                printSameUsers(this.responseText, workingDir);
            }
        };
        xhttp.open("GET", service2, true);
        xhttp.send();
    }
}

function callUsers(workingDir) {

    if (workingDir==null) workingDir = '';
    var service = workingDir + '/searchFriends2';
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState==4 && this.status==200) {
            printSameUsers(this.responseText,workingDir);
        }
    };
    xhttp.open("GET", service, true);
    xhttp.send();

}

function printSameUsers(jsonResponse,workingDir) {
    var out = "";
    var list = JSON.parse(jsonResponse);

    if (list.length !=0 && list!=null) {
        for (var key in list) {
            if (list.hasOwnProperty(key)) {
                var status = "";
                var my = JSON.parse(key);
                if (list[key] == "IN_FRIENDS") {
                    status = "This person is already in your friends";
                } else if (list[key] == "NOT_A_FRIEND") {
                    status = '<a href="' + workingDir + '/addFriend?friend=' + my.login + '">' +
                        '<button class="btn btn-primary">Invite</button></a>';
                } else if (list[key] == "INVITE_SENDED") {
                    status = "You already send an invite";
                } else if (list[key] == "INVITE_RECEIVED") {
                    status = '<a href="' + workingDir + '/approve?friend=' + my.login + '">' +
                        '<button class="btn btn-success">Approve</button></a>'
                }

                out += ' <div class="col-md-12" style="margin-top:15px;padding:10px;margin-bottom:15px;background-color: #ffffff;">' +
                    '<div class="col-md-12 panel-warning"><div class="col-md-3 panel-warning">' +
                    '<div><img style="padding8px;" width="120px" border="1px"' +
                    ' src="' + workingDir + '/ava/' + my.avatar + '"></div></div>' +
                    '<div class="col-md-9 panel-info"><div class="panel-title"><b>'+
                    '<a href=' + workingDir + '/user?userLogin=' + my.login + '>' +
                    my.login + '</a></b></div><br><br>' + status +
                    '</div></div></div></div>';
            }
        }
    }
    if (out==""){
        out += 'No result =(';
    }
    document.getElementById("listUsers").innerHTML = out;
}
