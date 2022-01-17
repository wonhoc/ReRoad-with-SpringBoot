$(document).ready(function() {

    $(document).on('click','#deleteBtn', function (){
        confirm("정말 삭제하시겠습니까?");
    })



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
                    if(comment == null){
                        str = '<tbody><tr><td colspan="5">등록된 댓글이 없습니다.</td></tr></tbody>'
                    }else {
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
                                str += '<td><button class="modifyComReqBtn" type="button">수정</button></td>';
                            }else{
                                str += '<td></td>'
                            }
                            if(currnetUser.userNick == comment[i].userNick || currnetUser.role == "ROLE_ADMIN"){
                                str += '<td><button class="removeBtn">삭제</button></td>';
                            }else{
                                str += '<td></td>';
                            }
                            str += '</tr>';
                        str += '</tbody>';
                    }
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
                        str += '<td><button class="modifyComReqBtn" type="button">수정</button></td>';
                    }else{
                        str += '<td></td>'
                    }
                    if(currnetUser.userNick == comment[i].userNick || currnetUser.role == "ROLE_ADMIN"){
                        str += '<td><button class="removeBtn">삭제</button></td>';
                    }else{
                        str += '<td></td>';
                    }
                    str += '</tr>';
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
                    if(comment[i].userNick == null){
                        str += '<tbody><tr><td colspan="5">등록된 댓글이 없습니다.</td></tr></tbody>'
                    }else {
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
                            str += '<td><button class="modifyComReqBtn" type="button">수정</button></td>';
                        }else{
                            str += '<td></td>'
                        }
                        if(currnetUser.userNick == comment[i].userNick || currnetUser.role == "ROLE_ADMIN"){
                            str += '<td><button class="removeBtn">삭제</button></td>';
                        }else{
                            str += '<td></td>';
                        }
                        str += '</tr>';
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

                alert(result);

            },
            error: function (err){

            }

        })
    })




});