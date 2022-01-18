

$(document).ready(function() {

    $('#deleteBtn').on('click', function (){
        Swal.fire({
            title: '정말로 그렇게 하시겠습니까?',
            text: "다시 되돌릴 수 없습니다. 신중하세요.",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '승인',
            cancelButtonText: '취소'
        }).then((result) => {
            if (result.isConfirmed) {
                var boardNo = $('#boardNo').val();
                location.href = '/member/deleteBoard/'+boardNo;
            }
        })
    })

    $(document).on('click', '.modifyComReqBtn', function (){
       $(this).parents('tbody').children('.modi').show()
    })

    $(document).on('click','.cancel', function (){
        $(this).parents('tbody').children('.modi').hide()
    })

    //댓글 취소
    $('#cancel').on('click', function() {
        const comNo = $('#comNo').val();
        $('#' + comNo).show();
        $('#modifyComment').hide();
        $('#modifyComment').insertAfter('#addComment');
    });


    $('#addComBtn').on('click',function () {

        if($('#comContent').val().length > 40){
            Swal.fire({ icon: 'warning', title: '경고', text: '40자 이내로 적어주세요' ,});
            return false;
        }else if($('#comContent').val() == ''){
            Swal.fire({ icon: 'warning', title: '경고', text: '댓글을 입력해주세요.' ,});
            return false;
        }

        $.ajax({
            url: "/createComment",
            type: 'POST',
            contentType: 'application/json;charset=utf-8',
            dataType: 'json',
            data: JSON.stringify({
                "boardNo" : $('#boardNo').val(),
                "comContent" : $('#comContent').val()
            }),
            success : function (data){

                let comCount = data.comCount;
                let currnetUser = data.currnetUser;

                $('#comCount').html("");

                let html = ' <span id="comCount" class="others">' + comCount + '</span>';

                $('#comCount').html(html);

                let comment = data.results;
                $("#listComment").html("");

                let str = "";
                for (let i = 0; i < comment.length; i++) {
                    str += '<tbody id = "' + comment[i].comNo + '">'
                        + '<tr>'
                        + '<td id="comNick" >'
                        + comment[i].userNick
                        + '</td>'
                        + '<td id="comWdate" >'
                        + comment[i].comWdate
                        + '</td>'
                        + '<td id="com" class="comContent">'
                        + comment[i].comContent
                        + '</td>';
                    if(currnetUser.userNick == comment[i].userNick || currnetUser.role == "ROLE_ADMIN"){
                        str += '<td><button style="width: 68px;" class="modifyComReqBtn" type="button">수정</button></td>';
                    }else{
                        str += '<td style="width: 68px;"></td>'
                    }
                    if(currnetUser.userNick == comment[i].userNick || currnetUser.role == "ROLE_ADMIN"){
                        str += '<td><button style="width: 68px;" class="removeBtn">삭제</button></td>';
                    }else{
                        str += '<td style="width: 68px;"></td>';
                    }
                    str += '</tr>';
                    str += '<tr class="modi" style="display: none">' +
                        '<td class="modifyForm" colspan="3"><input style="width: 500px" class="modifyComContent" value="' + comment[i].comContent + '"></td>'+
                        '<td><button style="width: 68px;" class="modifyComBtn">수정하기</button></td>'+
                        '<td><button style="width: 68px;" class="cancel">취소</button></td></tr>'
                    str += '</tbody>';
                }
                $("#listComment").html(str);

                var el = $('#comContent');

                for(var i=0; i<el.length; i++){

                    el[i].value = '';
                }

            },
            error : function (err){
                console.log(err)
            }

        })

    });

    $(document).on('click','.modifyComBtn', function (){
        let comNo = $(this).parents('tbody').attr('id');
        let comContent = $(this).parent().prev().children().val();
        console.log(comContent);
        $.ajax({
            url: '/modityComment',
            type: 'POST',
            contentType: 'application/json;charset=utf-8',
            dataType: 'json',
            data: JSON.stringify({
                "boardNo" : $('#boardNo').val(),
                "comNo" : comNo,
                "comContent" : comContent
            }),
            success : function (data){
                let currnetUser = data.currnetUser;
                let comment = data.results;
                $("#listComment").html("");

                let str = "";
                for (let i = 0; i < comment.length; i++) {
                    str += '<tbody id = "' + comment[i].comNo + '">'
                        + '<tr>'
                        + '<td id="comNick" >'
                        + comment[i].userNick
                        + '</td>'
                        + '<td id="comWdate" >'
                        + comment[i].comWdate
                        + '</td>'
                        + '<td id="com" class="comContent">'
                        + comment[i].comContent
                        + '</td>';
                    if(currnetUser.userNick == comment[i].userNick || currnetUser.role == "ROLE_ADMIN"){
                        str += '<td><button style="width: 68px;" class="modifyComReqBtn" type="button">수정</button></td>';
                    }else{
                        str += '<td style="width: 68px;"></td>'
                    }
                    if(currnetUser.userNick == comment[i].userNick || currnetUser.role == "ROLE_ADMIN"){
                        str += '<td><button style="width: 68px;" class="removeBtn">삭제</button></td>';
                    }else{
                        str += '<td style="width: 68px;"></td>';
                    }
                    str += '</tr>';
                    str += '<tr class="modi" style="display: none">' +
                        '<td class="modifyForm" colspan="3"><input style="width: 500px" class="modifyComContent" value="' + comment[i].comContent + '"></td>'+
                        '<td><button style="width: 68px;" class="modifyComBtn">수정하기</button></td>'+
                        '<td><button style="width: 68px;" class="cancel">취소</button></td></tr>'
                    str += '</tbody>';
                }
            $("#listComment").html(str);

            },
            error : function (err){
                console.log(err);

            }
        });
    });

    $(document).on('click','.removeBtn', function (){

        let comNo = $(this).parents('tbody').attr('id');
        $.ajax({
            url : '/removeComment',
            type : 'Post',
            contentType: 'application/json;charset=utf-8',
            dateType: 'JSON',
            data : JSON.stringify({
                    "boardNo" : $('#boardNo').val(),
                    "comNo" : comNo
                }),
            success : function (data) {
                let comCount = data.comCount;
                let currnetUser = data.currnetUser;


                $('#comCount').html("");

                let html = ' <span id="comCount" class="others">' + comCount + '</span>';

                $('#comCount').html(html);

                let comment = data.results;
                $("#listComment").html("");
                let str='';

                for (let i = 0; i < comment.length; i++) {
                    if (comment[i].userNick == null) {
                        str += '<tbody><tr><td colspan="5">등록된 댓글이 없습니다.</td></tr></tbody>'
                    } else {
                        str += '<tbody id = "' + comment[i].comNo + '">'
                            + '<tr>'
                            + '<td id="comNick" >'
                            + comment[i].userNick
                            + '</td>'
                            + '<td id="comWdate" >'
                            + comment[i].comWdate
                            + '</td>'
                            + '<td id="com" class="comContent">'
                            + comment[i].comContent
                            + '</td>';
                        if (currnetUser.userNick == comment[i].userNick || currnetUser.role == "ROLE_ADMIN") {
                            str += '<td><button style="width: 68px;" class="modifyComReqBtn" type="button">수정</button></td>';
                        } else {
                            str += '<td style="width: 68px;"></td>'
                        }
                        if (currnetUser.userNick == comment[i].userNick || currnetUser.role == "ROLE_ADMIN") {
                            str += '<td><button style="width: 68px;" class="removeBtn">삭제</button></td>';
                        } else {
                            str += '<td style="width: 68px;"></td>';
                        }
                        str += '</tr>';
                        str += '<tr class="modi" style="display: none">' +
                            '<td class="modifyForm" colspan="3"><input style="width: 500px" class="modifyComContent" value="' + comment[i].comContent + '"></td>' +
                            '<td><button style="width: 68px;" class="modifyComBtn">수정하기</button></td>' +
                            '<td><button style="width: 68px;" class="cancel">취소</button></td></tr>'
                        str += '</tbody>';
                    }
                }
                    $("#listComment").html(str);

            },
            error : function (err) {
                console.log(err)

            }
        });
    });

    $('#recomBtn').on('click', function (){
        $.ajax({
            url: '/recommend',
            type: 'POST',
            contentType: 'application/json;charset=utf-8',
            dataType: 'Json',
            data: JSON.stringify({
                "boardNo" : $('#boardNo').val()
            }),
            success: function (data){
                let result = data.results;
                let count = data.count;
                alert(result);

                $('#recomCount').html("");

                let str = '<span id="recomCount">'+ count +'</span>';
                $('#recomCount').html(str);

            },
            error: function (err){

            }

        })
    })

    $('#reportBtn').on('click', function (){
        $.ajax({
            url: '/report',
            type: 'POST',
            contentType: 'application/json;charset=utf-8',
            dataType: 'Json',
            data: JSON.stringify({
                "boardNo" : $('#boardNo').val(),
                "userId" : $('#userId').val()
            }),
            success: function (data){
                let result = data.results;
                Swal.fire({ icon: 'warning', title: '경고', text: result ,});

            },
            error: function (err){

            }

        })
    })




});