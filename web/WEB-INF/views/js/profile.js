var service = 'uploadFile';
$(document).ready(function() {
    $("#upload-file-input").on("change", uploadFile);
});

/**
 * Upload the file sending it via Ajax at the Spring.
 */
function uploadFile() {
    $.ajax({
        url: service,
        type: "POST",
        data: new FormData($("#upload-file-form")[0]),
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        success: function (data) {
            // Handle upload success
            $("#upload-file-message").text(data);
        },
        error: function () {
            // Handle upload error
            $("#upload-file-message").text(
                "Ошибка!");
        }
    });
} // function upload
/*function uploadJqueryForm(){

 $("#form2").ajaxForm({
 success:function(data) {
 $('#result').html(data);
 },
 dataType:"text"
 }).submit();


 }
 function uploadFormData(){
 $('#result').html('');

 var oMyForm = new FormData();
 oMyForm.append("file", file2.files[0]);

 $.ajax({
 url: 'http://localhost:8080/spring-mvc-file-upload/rest/cont/upload',
 data: oMyForm,
 dataType: 'text',
 processData: false,
 contentType: false,
 type: 'POST',
 success: function(data){
 $('#result').html(data);
 }
 });


 }*/
