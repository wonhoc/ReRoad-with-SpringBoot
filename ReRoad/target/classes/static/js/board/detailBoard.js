$(document).ready(function() {

    $('#listComment').on('click', '.modifyComReqBtn', function() {
        let comNo = $(this).parents('tbody').attr('id');
         alert(comNo);
            $('#modifyComment').insertAfter('#' + comNo);
            $('#modifyComContent').val(comContent);
            $('#comNo').val(comNo);
            $('#modifyComment').show();
            $('#' + comNo).hide();
        });

    //댓글 취소
    $('#cancel').on('click', function() {
        const comNo = $('#comNo').val();
        $('#' + comNo).show();
        $('#modifyComment').hide();
        $('#modifyComment').insertAfter('#addComment');
    });


    $('#addComBtn').on('click',function () {
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
                let comment = data.results;
                $("#listComment").html("");
                let str = '<thead><tr><td align="center" >작성자</td><td align="center" >날짜</td><td align="center" >내용</td><td></td><td></td></tr><thead>'
                for (let i = 0; i < comment.length; i++) {
                    str += '<tbody id = "' + comment[i].comNo + '">'
                        + '<tr>'
                        + '<td align="center" width="100px;" >'
                        + comment[i].userNick
                        + '</td>'
                        + '<td align="center" width="100px;" >'
                        + comment[i].comWdate
                        + '</td>'
                        + '<td align="center" width="400px;" class="comContent">'
                        + comment[i].comContent
                        + '</td>';
                        str	+= '<td><button class="modifyComReqBtn" type="button">수정</button></td>'
                            +'<td><button class="removeBtn">삭제</button></td>'
                            + '</tr>';
                    str += '</tbody>';
                }
                $("#listComment").html(str);

            },
            error : function (err){
                console.log(err)
            }

        })
    });

    $('.modifyComBtn').on('click',function () {

        alert($('#comNo').val());
        $.ajax({
            url: '/modityComment',
            type: 'POST',
            contentType: 'application/json;charset=utf-8',
            dataType: 'json',
            data: JSON.stringify({
                "boardNo" : $('#boardNo').val(),
                "comNo" : $('#comNo').val(),
                "comContent" : $('#modifyComContent').val(),
                "userId" : $('#userId').val()
            }),
            success : function (data){
                let comment = data.results;
                $("#listComment").html("");
                let str = '<thead><tr><td align="center" >작성자</td><td align="center" >날짜</td><td align="center" >내용</td><td></td><td></td></tr><thead>';
                for (let i = 0; i < comment.length; i++) {
                    str += '<tbody id = "' + comment[i].comNo + '">'
                        + '<tr>'
                        + '<td align="center" width="100px;" >'
                        + comment[i].userNick
                        + '</td>'
                        + '<td align="center" width="100px;" >'
                        + comment[i].comWdate
                        + '</td>'
                        + '<td align="center" width="400px;" class="comContent">'
                        + comment[i].comContent
                        + '</td>';
                        str	+= '<td><button class="modifyComReqBtn" type="button">수정</button></td>'
                            +'<td><button class="removeBtn">삭제</button></td>'
                            + '</tr>';
                    str += '</tbody>';
                }
                $("#listComment").html(str);

            },
            error : function (err){
                console.log(err);

            }
        });
    });

    $('.removeBtn').on('click',function () {

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
                let comment = data.results;
                $("#listComment").html("");
                let str = '<thead><tr><td align="center" >작성자</td><td align="center" >날짜</td><td align="center" >내용</td><td></td><td></td></tr><thead>';
                for (let i = 0; i < comment.length; i++) {
                    str += '<tbody id = "' + comment[i].comNo + '">'
                        + '<tr>'
                        + '<td align="center" width="100px;" >'
                        + comment[i].userNick
                        + '</td>'
                        + '<td align="center" width="100px;" >'
                        + comment[i].comWdate
                        + '</td>'
                        + '<td align="center" width="400px;" class="comContent">'
                        + comment[i].comContent
                        + '</td>';
                    str	+= '<td><button class="modifyComReqBtn" type="button">수정</button></td>'
                        +'<td><button class="removeBtn">삭제</button></td>'
                        + '</tr>';
                    str += '</tbody>';
                }
                $("#listComment").html(str);

            },
            error : function (err) {
                console.log(err)

            }
        });
    });


});