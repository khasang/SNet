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
            '<p><b>'+news[i].description + '</b></p>'+
           '<hr> </div>';
    }
    document.getElementById("listNews").innerHTML = out;
}

