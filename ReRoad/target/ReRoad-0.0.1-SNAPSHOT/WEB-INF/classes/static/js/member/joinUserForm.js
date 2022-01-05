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
                // beforeSend: function(xhr){
                //     xhr.setRequestHeader(header,token);
                // },
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

            } else if (result == 'true') {
                $('#checkDbId').text('사용 가능한 아이디입니다.');
                $('#checkDbId').css('color', 'RGB(1,121,122)');

                $('#sendVeriNum').attr("disabled", false);

            }
        } catch (error) {

        }

    }

    // 인증번호 발송 텍스트 변경
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
                    // beforeSend: function (xhr) {
                    //     xhr.setRequestHeader(header, token);
                    // },
                    success: function (data) {
                        alert("인증 번호가 전송되었습니다.메일을 확인하고 인증번호를 입력하세요.")
                        console.log("Data : " + data)
                        resultVerify = data;
                        console.log("resultVerify : " + resultVerify);
                        resolve(data)
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

        }
    })

    //다음 버튼 클릭 시 회원 가입 다음 단계로 이동
    $('#nextButton').click(function () {
        $('#joinFormOne').fadeOut();
        $('#joinFormTwo').fadeIn();

    })

    // 비밀번호 길이, 유효성(영문, 특수, 숫자 포함 규칙) 검증
    $('#userPwd').bind('keyup', function() {
        var pwdRule = /^(?=.*[a-zA-z])(?=.*[!@#$%^&*+=-])(?=.*[0-9]).{10,30}$/;
        var pass1 = $('#userPwd').val();

        if(pass1.length <10 || pass1.length > 20) {
            $('#checkPwdformat').text("비밀번호는 10~20자 사이로 입력해야 합니다.");
            $('#checkPwdformat').css('color','red');
        } else if (pass1.length >=10 && pwdRule.test(pass1) == true) {
            $('#checkPwdformat').text("사용할 수 있는 비밀번호 입니다.");
            $('#checkPwdformat').css('color','RGB(1,121,122)');
        } else if (pass1.length >=10 && pwdRule.test(pass1) == false) {
            $('#checkPwdformat').text("비밀번호는 영문 대소문자, 특수문자, 숫자를 포함해야 합니다");
            $('#checkPwdformat').css('color', 'red');
        }
    })
    // 비밀번호 재입력 확인
    $('#reInputPwd').bind('keyup', function () {
        var firstPwd = document.getElementById('userPwd').value;
        var secondPwd = document.getElementById('reInputPwd').value;
        if(firstPwd !== secondPwd) {
            $('#checkPwdOneMore').text("비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
            $('#checkPwdOneMore').css('color', 'red');
        } else {
            $('#checkPwdOneMore').text("비밀번호가 일치합니다.");
            $('#checkPwdOneMore').css('color', 'RGB(1,121,122)');
        }

    })
    //닉네임 중복 체크
    $('#userNick').bind('keyup', function() {
        const comeNick = $('#userNick').val();
        if(comeNick == "") {
            $('#checkNick').text("닉네임은 필수 입력 항목입니다.")
            $('#checkNick').css('color','red');
        } else {
            if(comeNick.length < 5 || comeNick.length >= 15) {
                $('#checkNick').text("닉네임은 5~15자 이내로 입력해야 합니다.");
                $('#checkNick').css('color','red');
            } else {
                nickChkProcess('/checkNick',comeNick);
            }
        }
    })
    //닉네임 중복 체크 Ajax
    const getNickAjax = function(url, comeNick) {
        return new Promise((resolve, reject) => {
            $.ajax({
                url: url,
                method: 'POST',
                data: {
                    userNick : comeNick
                },
                success: function(data) {
                    resolve(data);
                },
                error: function(e) {
                    reject(e);
                }
            })
        })
    }
    async function nickChkProcess(url, userNick) {
        try {
            const result = await getNickAjax(url, userNick);
            if (result == 'false') {
                $('#checkNick').text("이미 사용중인 닉네임입니다.");
                $('#checkNick').css('color', 'red');
            } else if (result == 'true') {
                $('#checkNick').text("사용 가능한 닉네임입니다.");
                $('#checkNick').css('color', 'RGB(1,121,122)');

            }
        } catch (error) {

        }
    }

    //입력한 데이터 최종 유효성 체크
    $('#joinButton').on('click', function() {
        if($('#userId').val() == "" || $('#userId').val() == null ||
            $('#userPwd').val()== "" ||  $('#userPwd').val() == null ||
            $('#reInputPwd').val()== "" || $('#reInputPwd').val() == null ||
            $('#userNick').val() == "" || $('#userNick').val() == null) {
            alert("입력되지 않은 항목이 있습니다. 다시 확인해주세요.")
        } else {
            $('#joinUserForm').submit();
        }
    })

})

