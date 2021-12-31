$(document).ready(function() {
    //아이디 중복 체크
    $('username').bind('focusout', function () {
        const userId = $('#userId').val();
        if (userId == "") {
            $('#checkDbId').text('아이디는 필수 입력 항목입니다.');
            $('#checkDbId').css('color', 'red');
            $('#sendVeriNum').attr("disabled", true);
        } else {
            idchkProcess('/checkId', userId);
        }
    });

    const getIdAjax = function (url, userId) {
        return new Promise((resolve, reject) => {
            $.ajax({
                url: url,
                method: 'POST',
                data: {
                    username: userId
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
            if (result == 'true') {
                $('#checkDbId').text('사용할 수 없는 아이디입니다.');
                $('#checkDbId').css('color', 'red');
                $('#sendVeriNum').attr("disabled", true);
            } else if (result == 'false') {
                $('#checkDbId').text('사용 가능한 아이디입니다.');
                $('#checkDbId').css('color', 'RGB(242,242,242)');
                $('#sendVeriNum').attr("disabled", false);
            }
        } catch (error) {

        }

    }

});