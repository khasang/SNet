function LoadNewsAndMembers(id) {
    callMembers(id);
    callNews(id)
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
    for (i = 0; i < news.length; i++) {
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
            '<td>'+ members[i].login + '</td>'+
            '</tr>';

    }
    out+='</table>';
    if (members.length ==0){
        out= '<p>'+"Workgroup doesn't have members"+'</p>'
    }
    document.getElementById("listMembers").innerHTML = out;
}
