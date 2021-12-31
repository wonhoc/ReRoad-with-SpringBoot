$(document).ready(function() {



    $('#searchBtn').on('click',function () {
        $.ajax({
            url: "/search",
            type: 'POST',
            contentType: 'application/json;charset=utf-8',
            dataType: 'json',
            data: JSON.stringify({
                "keyfield": $('#keyfield').val(),
                "keyword": $('#keyword').val()
            }),
            success: function (data) {
                let boardList = data.results;
                $('#html').html("");
                let str = "";


                for (let i = 0; i< boardList.length ; i++){

                    str +='<table id="boardTable">' +
                                '<tr>' +
                                    '<td rowSpan="2" colSpan="2"><img height="210px;" width="210px;" className="thumnail" src="/images/board/king.png"></td>' +
                                    '<td id="mm" colSpan="3"><a href="/detailBoard/' + boardList[i].boardNo + '">'+ boardList[i].boardTitle+'</a></td>'+
                                '</tr>' +
                                '<tr>' +
                                    '<td><img width="20px;" height="20px;" className="boardImg" src="/images/board/user.png"><span>' + boardList[i].userNick + '</span></td>' +
                                    '<td><img width="20px;" height="20px;" className="boardImg" src="/images/board/view.png"><span>' + boardList[i].boardCount+'</span></td>' +
                                    '<td><img width="20px;" height="20px;" className="boardImg" src="/images/board/commentCount.png"><span>'+ boardList[i].boardCount + '</span></td>' +
                                '</tr>' +
                                '<tr>' +
                                    '<td height="50px;" colSpan="5">' + boardList[i].boardContent + '</td>' +
                                '</tr>'+
                            '</table>'
                }
                $('#html').html(str);

            },
            error: function (err) {
                alert(err);
            }
        });
    });


});