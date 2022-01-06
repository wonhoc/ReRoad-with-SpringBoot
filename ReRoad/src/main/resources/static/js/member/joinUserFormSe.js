$(document).ready(function() {
    //회원가입 버튼 기본 비활성화
    $('#joinButton').attr('disabled', true);

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