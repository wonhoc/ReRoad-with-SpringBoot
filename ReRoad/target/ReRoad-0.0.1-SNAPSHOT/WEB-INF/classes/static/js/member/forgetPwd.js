$(document).ready(function() {

    var resultVerify = "";

    // 버튼 비활성화 -> 조건 충족 시 활성화됨
    $('#sendTempPwd').attr("disabled", true);

    //아이디 중복 검사 => 아이디가 있을 경우에만 인증번호 발송 가능
    $('#userId').bind('keyup', function() {
        const userId = $('#userId').val();
        idchkProcess2('/checkId', userId);
    })

    const getIdAjax2 = function (url, userId) {
        return new Promise((resolve, reject) => {
            $.ajax({
                url: url,
                method: 'POST',
                data: {
                    userId: userId
                },
                success: function(data) {
                    resolve(data);
                },
                error: function (e) {
                    reject(e);
                }


            })

        })
    }
    async function idchkProcess2(url, userId) {
        try {
            const result = await getIdAjax2(url, userId);

            if (result == "false") {
                $('#checkDbId').text("가입 되어 있는 이메일입니다.")
                $('#checkDbId').css('color', 'RGB(1,121,122)');
                $('#sendTempPwd').attr("disabled", false);
            } else if(result == "true") {
                $('#checkDbId').text("등록되지 않은 이메일입니다.")
                $('#checkDbId').css('color', 'red');
                $('#sendTempPwd').attr("disabled", true);
            }

        } catch (e) {
            printStackTrace(e);
        }
    }








})