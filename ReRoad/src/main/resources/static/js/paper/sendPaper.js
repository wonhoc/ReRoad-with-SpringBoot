/*메뉴 선택에 따라 화면 전환 구현 */

$('input[type=radio][name=paperRadio]').on('click', function() {
    var chkValue = $('input[type=radio][name=paperRadio]:checked').val();

    if (chkValue == 'write') {
        $('#writePaper').css('display','block');

    } else if (chkValue == 'send') {
        $('#writePaper').css('display','none');
    } else if (chkValue == 'receive') {
        $('#writePaper').css('display','none');
    }
})