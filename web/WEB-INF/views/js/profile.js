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
}
