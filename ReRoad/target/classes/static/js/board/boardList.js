$(document).ready(function() {

    $(document).on('click', '#writeBtn', function(){

        location.href='/writeboardForm';

    })


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
                $('#pagination-div').remove();
                $('#pagination-div2').twbsPagination('destroy');

                let html2 = "<div id='pagination-div2'>" +
                    "<div id='page-content2'></div>" +
                    " </div>";
                $("#paging").html(html2);
                let str = "";
                console.log(boardList);
                if(boardList.length == 0){
                    str+='<table id="boardTable"><tr><td>검색 결과가 없습니다.</td></tr></table>';
                }else {
                    $('#pagination-div2').twbsPagination({
                        totalPages: Math.ceil(boardList.length / 10),   // 총 페이지 번호 수
                        visiblePages: 5,   // 하단에서 한번에 보여지는 페이지 번호 수
                        startPage: 1, // 시작시 표시되는 현재 페이지
                        initiateStartPageClick: true,   // 플러그인이 시작시 페이지 버튼 클릭 여부 (default : true)
                        first: "<<",   // 페이지네이션 버튼중 처음으로 돌아가는 버튼에 쓰여 있는 텍스트
                        prev: "<",   // 이전 페이지 버튼에 쓰여있는 텍스트
                        next: ">",   // 다음 페이지 버튼에 쓰여있는 텍스트
                        last: ">>",   // 페이지네이션 버튼중 마지막으로 가는 버튼에 쓰여있는 텍스트
                        nextClass: "page-item next",   // 이전 페이지 CSS class
                        prevClass: "page-item prev",   // 다음 페이지 CSS class
                        lastClass: "page-item last",   // 마지막 페이지 CSS calss
                        firstClass: "page-item first",   // 첫 페이지 CSS class
                        pageClass: "page-item",   // 페이지 버튼의 CSS class
                        activeClass: "active",   // 클릭된 페이지 버튼의 CSS class
                        disabledClass: "disabled",   // 클릭 안된 페이지 버튼의 CSS class
                        anchorClass: "page-link",   //버튼 안의 앵커에 대한 CSS class

                        onPageClick: function (event, page) {
                            //클릭 이벤트
                            let html = "<tr>";
                            let startRow = (page - 1) * 10;
                            let endRow = page * 10;
                            let totalCount = boardList.length
                            if (endRow > totalCount) {
                                endRow = totalCount;
                            }
                            let totalPages = Math.ceil(totalCount / 10);
                            if (totalCount % 10 > 0) {
                                totalPages++;
                            }
                            let startPage = ((page - 1) / 5) * 5 + 1;
                            let endPage = startPage + 5 - 1;
                            if (endPage > totalPages) {    //
                                endPage = totalPages;
                            }
                            for (let i = startRow; i < endRow; i++) {

                                str += '<table id="boardTable">' +
                                    '<tr>' ;
                                if(boardList[i].boardFiles == ''){
                                    str += '<td rowSpan="2" colSpan="2"><img height="210px;" width="210px;" className="thumnail" src="/images/board/king.png"></td>' ;
                                }
                                if(boardList[i].boardFiles!= ''){
                                    str +='<td rowSpan="2" colSpan="2"><img height="210px;" width="210px;" className="thumnail" src="/upload/' + boardList[i].boardFiles[0].systemFileName + '"></td>';
                                }
                                str += '<td id="mm" colSpan="3"><a href="/detailBoard/' + boardList[i].boardNo + '">' + boardList[i].boardTitle + '</a></td>' +
                                    '</tr>' +
                                    '<tr>' +
                                    '<td><img width="20px;" height="20px;" className="boardImg" src="/images/board/user.png"><span>' + boardList[i].userNick + '</span></td>' +
                                    '<td><img width="20px;" height="20px;" className="boardImg" src="/images/board/view.png"><span>' + boardList[i].boardCount + '</span></td>' +
                                    '<td><img width="20px;" height="20px;" className="boardImg" src="/images/board/commentCount.png"><span>' + boardList[i].boardCount + '</span></td>' +
                                    '</tr>' +
                                    '<tr>' +
                                    '<td height="50px;" colSpan="5">' + boardList[i].boardContent + '</td>' +
                                    '</tr>' +
                                    '</table>';

                            }

                            $('#html').html(str);
                        }
                    })

                }


            },
            error: function (err) {
                alert(err);
            }
        });
    });


});