$(document).ready(function () {
    const saveBtn = document.getElementById('saveBtn');

    //공지글 제목 유효성 체크
    $('#noticeTitle').bind('focusout', function() {
        const noticeTitle = $('#noticeTitle').val();
        var regex = / /gi;

        if(noticeTitle == "" ||  noticeTitle.replace(regex, '') == "") {
            $('#Isvalid').text('제목을 입력하세요');
            $('#Isvalid').css('color', 'red');
            $('#saveBtn').attr("disabled",true);
        }  else {
            $('#Isvalid').css('color', 'white');
            $('#saveBtn').attr("disabled",false);
        };
    });
//공지글 제목 클릭시 다시 버튼 활성화
    $('#noticeTitle').bind('click', function() {
        $('#saveBtn').attr("disabled",false);

    });
    //공지글 본문 유효성 체크
    $('#noticeContent').bind('focusout', function() {
        const noticeContent = $('#noticeContent').val();
        var regex = / /gi;
        if(noticeContent == "" || noticeContent.replace(regex, '') == "") {
            $('#Isvalid').text('본문을 입력하세요');
            $('#Isvalid').css('color', 'red');
            $('#saveBtn').attr("disabled",true);
        }  else {
            $('#Isvalid').css('color', 'white');
            $('#saveBtn').attr("disabled",false);
        };
    });

    //공지글 본문
    $('#noticeContent').bind('click', function() {
            $('#saveBtn').attr("disabled",false);

    });
})