$(document).ready(function () {
    const saveBtn = document.getElementById('saveBtn');

    //여행이름 유효성 체크
    $('#travelTitle').bind('focusout', function () {
        const travelTitle = $('#travelTitle').val();
        if (travelTitle == "") {
            $('#Isvalid').text('여행이름을 입력하세요');
            $('#Isvalid').css('color', 'red');
            $('#saveBtn').attr("disabled", true);
        } else {
            $('#Isvalid').css('color', 'white');
            $('#saveBtn').attr("disabled", false);
        }
        ;
    });
//여행 이름란 클릭시 다시 버튼 활성화
    $('#travelTitle').bind('click', function () {

        $('#saveBtn').attr("disabled", false);

    });
    //여행지 유효성 체크
    $('#spot').bind('focusout', function () {
        const spot = $('#spot').val();
        if (spot == "") {
            $('#Isvalid').text('여행지를 입력하세요');
            $('#Isvalid').css('color', 'red');
            $('#saveBtn').attr("disabled", true);
        } else {
            $('#Isvalid').css('color', 'white');
            $('#saveBtn').attr("disabled", false);
        }
        ;
    });

    //여행지란 다시 클릭시 경고 메시지 지우기
    $('#spot').bind('click', function () {
        $('#saveBtn').attr("disabled", false);
    });


    //여행시작일란 다시 클릭시 경고 메시지 지우기
    $('#startDate').bind('onfocusin', function () {
        $('#saveBtn').attr("disabled", false);

    });


    //여행도착일란 다시 클릭시 버튼 활성화
    $('.datepicker').bind('click', function () {
        $('#saveBtn').attr("disabled", false);

    });

//메모 유효성 체크
    $('#memo').bind('focusout', function () {
        const memo = $('#memo').val();
        if (memo == "") {
            $('#Isvalid').text('여행 메모를 입력하세요');
            $('#Isvalid').css('color', 'red');
            $('#saveBtn').attr("disabled", true);
        } else {
            $('#Isvalid').css('color', 'white');
            $('#saveBtn').attr("disabled", false);
        }
        ;
    });

    //여행메모란 다시 클릭시 경고 메시지 지우기
    $('#memo').bind('click', function () {
        $('#saveBtn').attr("disabled", false);

    });

    $('#itemAdd').bind('click', function () {
        const chkCnt = form.checkListContent.length;
        if (chkCnt > 49) {
            $('#Isvalid').text('체크리스트는 50개까지만 입력 가능합니다!');
            $('#Isvalid').css('color', 'red');
            $('#itemAdd').attr("disabled", true);
        }

    });

    $('#itemDel').bind('click', function () {
        const chkCnt = form.checkListContent.length;
        if (chkCnt < 50) {
            $('#Isvalid').css('color', 'white');
            $('#itemAdd').attr("disabled", false);
        }
    });

    $('#saveBtn').bind('click', function () {
        const travelTitle = $('#travelTitle').val();
        const spot = $('#spot').val();
        const startDate = $('#startDate').val();
        const arriveDate = $('#arriveDate').val();
        const memo = $('#memo').val();
        const item = $("input[name='checkListContent']").val();
       var regex = / /gi;

        if (travelTitle == "" || travelTitle.replace(regex, '') == "" || travelTitle == null) {
            $('#Isvalid').text('여행 이름을 입력하세요!');
            $('#Isvalid').css('color', 'red');
            $('#saveBtn').attr("disabled", true);
        } else if (spot == "" || spot.replace(regex, '') == "" || spot == null) {
            $('#Isvalid').text('여행지를 입력하세요!');
            $('#Isvalid').css('color', 'red');
            $('#saveBtn').attr("disabled", true);
        } else if (startDate == "" || startDate.replace(regex, '') == "" || startDate == null) {
            $('#Isvalid').text('여행 출발일을 입력하세요!');
            $('#Isvalid').css('color', 'red');
            $('#saveBtn').attr("disabled", true);
        } else if (arriveDate == "" || arriveDate.replace(regex, '') == "" || arriveDate == null) {
            $('#Isvalid').text('여행 도착일을 입력하세요!');
            $('#Isvalid').css('color', 'red');
            $('#saveBtn').attr("disabled", true);
        } else if (memo == "" || memo.replace(regex, '') == "" || memo == null) {
            $('#Isvalid').text('여행 메모를 입력하세요!');
            $('#Isvalid').css('color', 'red');
            $('#saveBtn').attr("disabled", true);
        }  /*else if (item == "" || item.replace(regex, '') == "" || item == null) {
            $('#Isvalid').text('체크리스트를 입력하세요!');
            $('#Isvalid').css('color', 'red');
            $('#saveBtn').attr("disabled", true);
        }*/
    });
})
