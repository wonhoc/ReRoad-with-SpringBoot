$(document).ready(function() {

    $('#searchBtn').click(function () {
        $.ajax({
            url: "/search",
            type: 'POST',
            contentType: 'application/json;charset=utf-8',
            dataType: 'json',
            data: JSON.stringify({
                "keyfield": $('#keyfield').val(),
                "keyword": $('#keyword').val()
            }),
            success: function (data) {
                const code = data.code;
                alert(code);
            },
            error: function (err) {
                alert(err);
            }
        });
    });
});