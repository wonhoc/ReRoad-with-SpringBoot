$.datepicker.setDefaults({
    closeText: '닫기',
    prevText: '이전달',
    nextText: '다음달',
    currentText: '오늘',
    monthNames: ['1월(JAN)', '2월(FEB)', '3월(MAR)', '4월(APR)', '5월(MAY)', '6월(JUN)',
        '7월(JUL)', '8월(AUG)', '9월(SEP)', '10월(OCT)', '11월(NOV)', '12월(DEC)'],
    monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월',
        '7월', '8월', '9월', '10월', '11월', '12월'],
    dayNames: ['일', '월', '화', '수', '목', '금', '토'],
    dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
    dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
    weekHeader: 'Wk',
    dateFormat: 'yy-mm-dd',
    firstDay: 0,
    isRTL: false,
    showMonthAfterYear: true,
    yearSuffix: '년',
    showOn: 'both',
    buttonText: "달력",
    changeMonth: true,
    changeYear: true,
    showButtonPanel: true,
    yearRange: 'c-99:c+99',
    minDate: 0
});
$(function () {
    $('#startDate').datepicker();
    $('#startDate').datepicker("option", "maxDate", $("#arriveDate").val());
    $('#startDate').datepicker("option", "onClose", function (selectedDate) {
        $("#arriveDate").datepicker("option", "minDate", selectedDate);
    });

    $('#arriveDate').datepicker();
    $('#arriveDate').datepicker("option", "minDate", $("#startDate").val());
    $('#arriveDate').datepicker("option", "onClose", function (selectedDate) {
        $("#startDate").datepicker("option", "maxDate", selectedDate);
    });

    $("input[name='allchk']").click(function () {
        var chk_listArr = $("input[name='chk']");
        for (var i = 0; i < chk_listArr.length; i++) {
            chk_listArr[i].checked = this.checked;
        }
    });
});

function item_Add() {
    addRow = document.all("itemList").insertRow();

    //체크박스
    var addCol_chk = addRow.insertCell();
    addCol_chk.innerHTML = "<input type='checkbox' name='chk' />";

    //아이템 내용
    var addCol_content = addRow.insertCell();
    addCol_content.innerHTML = "<input type='text'  class='input' name='checkListContent' placeholder='아이템 입력..'  autocomplete='off' maxlength='10'>";

    //준비여부
    var addCol_radio = addRow.insertCell();
    addCol_radio.innerHTML = "<select name='ready'>" +
        "<option value='0'>준비 못함!</option>" +
        "<option value='1'>준비 완료!</option>" +
        "</select> ";
}

function item_del() {
    var tableData = document.getElementById('itemTable');
    for (var i = 1; i < tableData.rows.length; i++) {
        var chkbox = tableData.rows[i].cells[0].childNodes[0].checked;

        if (chkbox) {
            tableData.deleteRow(i);
            i--;
        }
    }
}
//동적으로 생성된 체크리스트를 위한 유효성 검사 코드
$(function () {
    $("#form").validate({
        errorElement: "label",
        errorPlacement: function(error, element) {
            error.insertAfter('#Isvalid');
            error.css("color", "red");
        },
        //규칙
        rules: {
            checkListContent: {
                spaceCheck: true,
                maxlength: 10
            }
        },
        //규칙체크 실패시 출력될 메시지
        messages: {
            checkListContent: {
                spaceCheck: "체크리스트를 올바르게 입력 후 다시 시도하세요",
                maxlength: "체크리스트 내용은 최대 10글자입니다."
            }
        }
    });

    $.validator.addMethod(
        "spaceCheck", //validate명
        function (value, element) {
            //검사하는  value가 공백이면 false리턴
            var blank_pattern = /^\s+|\s+$/g;
            if($(element).val()==''||$(element).val().replace(blank_pattern, '' ) == ""){
                return false;
            } else return true;
            //false 리턴 시 messages에 선언된 내용들 띄워줌
        }
    );

    //확장판 name 처리
    $.extend($.validator.prototype, {
        checkForm: function () {
            this.prepareForm();
            for (var i = 0, elements = (this.currentElements = this.elements()); elements[i]; i++) {
                if (this.findByName(elements[i].name).length != undefined && this.findByName(elements[i].name).length > 1) {
                    for (var cnt = 0; cnt < this.findByName(elements[i].name).length; cnt++) {
                        this.check(this.findByName(elements[i].name)[cnt]);
                    }
                } else {
                    this.check(elements[i]);
                }
            }
            return this.valid();
        }
    });
});