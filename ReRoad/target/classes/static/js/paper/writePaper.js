$(document).ready(function() {
    // 쪽지의 글자수 100자 제한
    $('#sendPaperContent').on('keyup',function() {
        $('#checkResult').html($(this).val().length);

        if($(this).val().length > 100) {
            $(this).val($(this).val().substring(0,100));
            $('#checkResult').html("100");
        }
    })



})