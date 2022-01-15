$(document).ready(function () {
    //공지글 제목 유효성 체크
    $('#noticeTitle').bind('focusout', function () {
        const noticeTitle = $('#noticeTitle').val();
        var regex = / /gi;

        if (noticeTitle == "" || noticeTitle.replace(regex, '') == "") {
            $('#Isvalid').text('제목을 입력하세요');
            $('#Isvalid').css('color', 'red');
            $('#saveBtn').attr("disabled", true);
        } else {
            $('#Isvalid').css('color', 'white');
            $('#saveBtn').attr("disabled", false);
        }
    });

    //공지글 제목 클릭시 다시 버튼 활성화
    $('#noticeTitle').bind('click', function () {
        $('#saveBtn').attr("disabled", false);
    });
    //파일삭제시 다시 버튼 활성화
    $('#removeFileBtn').bind('click', function () {
        $('#saveBtn').attr("disabled", false);
    });
    //공지글 본문 유효성 체크
    $('#noticeContent').bind('focusout', function () {
        const noticeContent = $('#noticeContent').val();
        var regex = / /gi;
        if (noticeContent == "" || noticeContent.replace(regex, '') == "") {
            $('#Isvalid').text('본문을 입력하세요');
            $('#Isvalid').css('color', 'red');
            $('#saveBtn').attr("disabled", true);
        } else {
            $('#Isvalid').css('color', 'white');
            $('#saveBtn').attr("disabled", false);
        }
    });

    //공지글 본문
    $('#noticeContent').bind('click', function () {
        $('#saveBtn').attr("disabled", false);
    });

    //파일 이름 띄우기 및 유효성 검사
    $("input[type=file]").change(function () {
        $('#file_name').empty();
        $('#saveBtn').attr("disabled",false);
        var fileInput = document.getElementById("noticeFileInput");
        var files = fileInput.files;
        var file=null;
        let html = "<ul>";

        if(files.length==1){
            if(files[0].size>10485760){
                const sizeErr = '  파일크기 초과!(최대 10MB)';
                html += "<li class='errli' id='fileName["+i+"]'>"+sizeErr+"</li>";
                $('#saveBtn').attr("disabled", true);
                $("#file_name").html(html);
            }
        } else {
            for (var i = 0; i < files.length; i++) {
                file = files[i];
                if (file.size > 10485760)  //파일이 10MB이상이면
                {
                    const sizeErr = '  파일크기 초과!(최대 10MB)';
                    html += "<li class='errli' id='fileName[" + i + "]'>" + file.name + sizeErr + "</li>";
                    $('#saveBtn').attr("disabled", true);
                } else {
                    html += "<li id='fileName[" + i + "]'>" + file.name + "</li>";
                    $('#fileName[i]').css('color', 'blue');
                }
                $("#file_name").html(html);
            }
        }
    });

    //저장 버튼 눌렀을 시  최종 유효성 검증
    $('#saveBtn').bind('click', function () {
        const noticeTitle = $('#noticeTitle').val();
        const noticeContent = $('#noticeContent').val();
        var regex = / /gi;
        var $fileUpload = $("input[type='file']");
        const originFile = parseInt($('#originFileSize').val());

        if (noticeTitle == "" || noticeTitle.replace(regex, '') == "") {
            $('#Isvalid').text('제목을 입력하세요');
            $('#Isvalid').css('color', 'red');
            $('#saveBtn').attr("disabled", true);
        } else if (noticeContent == "" || noticeContent.replace(regex, '') == "") {
            $('#Isvalid').text('본문을 입력하세요');
            $('#Isvalid').css('color', 'red');
            $('#saveBtn').attr("disabled", true);
        } else if (parseInt($fileUpload.get(0).files.length)+originFile>5){
            $('#Isvalid').text('파일은 최대 5개까지만 첨부 가능합니다.');
            $('#Isvalid').css('color', 'red');
            $('#saveBtn').attr("disabled", true);
        }
    });

})