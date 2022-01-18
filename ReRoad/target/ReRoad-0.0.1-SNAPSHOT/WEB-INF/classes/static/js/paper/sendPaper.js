$(document).ready(function() {
    //전체 삭제 클릭 시
    $('#allChecked').on('click',function() {
        // 전체 체크를 할 때
        if ($('#allChecked').is(':checked')) {
            //체크를 true로
            $("input:checkbox[name=removeCheckBox]").prop("checked",true);
        } else {
            //체크를 false로
            $("input:checkbox[name=removeCheckBox]").prop("checked",false);
        }
    })


})



