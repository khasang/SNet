function LoadNewsAndMembers(id) {
    callMembers(id);
    callNews(id);
    callNotMembers(id);
}

function callNews(id) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState==4 && this.status==200) {
            printAllNews(this.responseText);
        }
    };
    xhttp.open("GET", "/news/"+ id, true);
    xhttp.send();
}

function printAllNews(jsonResponse) {

    var news = JSON.parse(jsonResponse);
    var out = "";
    var i;
    for (i = news.length-1; i >=0 ; i--) {
        // out += '<div class="content-box-large">' +
        //     + '<h4><b><a href="">' + news[i].title +
        //     '</a></b></h4>'
        //     + '<p><i class="glyphicon glyphicon-dashboard"></i>' +news[i].newsDate + '</p>' +
        //     '<hr>'+
        //     '<a href="blog-post.html">'+
        //     '<img class="img-responsive img-hover" src="http://placehold.it/900x300" alt="">'+
        //     '</a><hr>'+
        //     + '<p>' +news[i].description + '</p>'+
        //     '<hr> </div>';
        // Не трогать , работает почемуто. но не знаю почему!
        out += '<div class="content-box-large">'  + '<h4><b><a href="">' + news[i].title + '</a></b></h4>'
            + '<p><i class="glyphicon glyphicon-dashboard"></i>' + ' ' + news[i].newsDate + '</p>' +
            '<hr>'+
            '<a href="">'+
                '<img class="img-responsive img-hover" src="http://placehold.it/900x300" alt="">'+
                '</a><hr>'+
            '<p>'+news[i].description + '</p>'+
           '<hr> </div>';
    }
    document.getElementById("listNews").innerHTML = out;
}

function callMembers(id) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState==4 && this.status==200) {
            printAllMembers(this.responseText);
        }
    };
    xhttp.open("GET", "/members/"+ id, true);
    xhttp.send();
}

function printAllMembers(jsonResponse) {

    var members = JSON.parse(jsonResponse);
    var out = '<table class="table">';
    var i;

    for (i = 0; i < members.length; i++) {
        out +='<tr>'+
            '<td><a href="user?userLogin='+ members[i].login +'" >'+members[i].login +'</a></td>'+
            '</tr>';
    }
    out+='</table>';
    if (members.length ==0){
        out= '<p>'+"Workgroup doesn't have members"+'</p>'
    }
    document.getElementById("listMembers").innerHTML = out;
}

function callNotMembers(id) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState==4 && this.status==200) {
            printNotMembers(this.responseText);
        }
    };
    xhttp.open("GET", "/notMembers/"+ id, true);
    xhttp.send();
}


function printNotMembers(jsonResponse) {

    var members = JSON.parse(jsonResponse);
    var out = '';
    var i;
    for (i = 0; i < members.length; i++) {
        // <option value="id">login</option>
        out +='<option value="'+members[i].id +'">'+members[i].login + '</option>';
    }
    document.getElementById("listNotMembers").innerHTML = out;
}

function addUserToWorkgroup(workgroupId) {
    var userId = document.getElementById("listNotMembers").value;
    if (userId != "" && userId != " ") {
        var userWorkgroups = {
            'id':0,
            'prKey': {
                'workgroupId':workgroupId,
                'userId':userId
            },
            'admin' : false
        };
        var json = JSON.stringify(userWorkgroups);
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState==4 && this.status==200) {
                callNotMembers(workgroupId);
                callMembers(workgroupId);
            }
        };
        xhttp.open("POST","/members/new",true);
        xhttp.send(json);
    }
}


function addWorkgroupNews(workgroupId) {
    var newsTitle = document.getElementById("workNewsTitle").value;
    var newsDescription = document.getElementById("workNewsDescr").value;
    if (newsTitle != "" && newsTitle != " ") {
        var userWorkgroupsNews = {
            'id': 0,
            'workgroupId': workgroupId,
            'title': newsTitle,
            'description': newsDescription
        };
        var json = JSON.stringify(userWorkgroupsNews);
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState==4 && this.status==200) {
                callNews(workgroupId);
                document.getElementById("workNewsTitle").value = "";
                document.getElementById("workNewsDescr").value = "";
            }
        };
        xhttp.open("POST","/news/new",true);
        xhttp.send(json);
    }
}




