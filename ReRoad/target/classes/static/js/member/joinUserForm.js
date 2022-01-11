$(document).ready(function() {

    var clickCount = false;

    // var token = $("meta[name='_csrf']").attr("content");
    // var header = $("meta[name='_csrf_header']").attr("content");
    var resultVerify = "";

    // 버튼 기본 비활성화
    $('#sendVeriNum').attr("disabled", true);
    $('#nextButton').attr("disabled", true);


    //아이디 중복, 유효성 체크
    $('#userId').bind('keyup', function () {
        const userId = $('#userId').val();
        var idRule = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
        $('#verifiedMail').val(userId);
        if (userId == "") {
            $('#checkDbId').text('아이디는 필수 입력 항목입니다.');
            $('#checkDbId').css('color', 'red');
        } else {
            if(idRule.test(userId) == false) {
                $('#checkDbId').text('이메일 주소 형식에 맞지 않습니다.');
                $('#checkDbId').css('color', 'red');
                $('#sendVeriNum').attr("disabled", true);
            } else {
                idchkProcess('/checkId', userId);
            }

        }
    });

    const getIdAjax = function (url, userId) {
        return new Promise((resolve, reject) => {
            $.ajax({
                url: url,
                method: 'POST',
                data: {
                    userId: userId
                },
                success: function (data) {
                    resolve(data);
                },
                error: function (e) {
                    reject(e);
                }
            });
        });
    };

    async function idchkProcess(url, userId) {
        try {
            const result = await getIdAjax(url, userId);

            if (result == 'false') {
                $('#checkDbId').text('이미 사용중인 이메일입니다.');
                $('#checkDbId').css('color', 'red');
                $('#sendVeriNum').attr("disabled", true);

            } else if (result == 'true') {
                $('#checkDbId').text('사용 가능한 아이디입니다.');
                $('#checkDbId').css('color', 'RGB(1,121,122)');
                $('#sendVeriNum').attr("disabled", false);

            }
        } catch (error) {

        }

    }

    // 인증번호 발송 버튼 텍스트 변경
    $('#sendVeriNum').click(function () {
        clickCount = true;
        if (clickCount) {
            $('#sendVeriNum').text('인증번호재발송');
        }
    })

    //인증번호 발송 Ajax
    $('#sendVeriNum').click(function () {
        const mail = $('#userId').val();
        verifyAjax('/verifyEmail', mail);
    })

    const verifyAjax = function (url, mail) {
        return new Promise(((resolve, reject) => {
                $.ajax({
                    type: 'POST',
                    url: url,
                    data: {
                        mail: mail
                    },
                    success: function (data) {
                        resultVerify = data;
                        resolve(data)
                        console.log("resultVerify : " + resultVerify);
                        if (resultVerify == "Error") {
                            alert("인증 번호 발송에 실패하였습니다. 입력한 내용을 확인 후 다시 시도해주세요.")
                        } else {
                            alert("인증 번호가 발송되었습니다.메일을 확인하고 인증번호를 입력하세요.");
                        }
                    },
                    error: function (e) {
                        alert("인증 번호 전송에 실패하였습니다. 잠시 후 다시 이용해주세요.")
                        reject(e)
                    }
                })
            }
        ));
    }

    // 인증 번호 검증
    $('#veriNumber').bind('keyup', function () {
        const inputNum = $('#veriNumber').val();

        if (inputNum == resultVerify) {
            $('#checkVeriNumber').text("인증번호가 일치합니다.");
            $('#checkVeriNumber').css('color', 'RGB(1,121,122)');
            $('#nextButton').attr("disabled", false);
        } else {
            $('#checkVeriNumber').text("인증번호가 일치하지 않습니다.");
            $('#checkVeriNumber').css('color', 'red');
            $('#joinButton').attr('disabled',true);

        }
    })
})

