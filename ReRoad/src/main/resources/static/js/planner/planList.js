$(document).ready(function () {


    $.ajax({
        url : '/getPlanList',
        method: 'GET',
        dataType: 'json',
        success: function (data) {
            console.log(data);

            if (data.length == 0) {
                let html3 = "<tr><td>아직 등록된 일정이 없어요!</td></tr>";
                $("tbody").html(html3);
            } else {
                $('#pagination-div').twbsPagination({
                    totalPages: Math.ceil(data.length / 10),   // 총 페이지 번호 수
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
                        let totalCount = data.length
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

                            //html += '<p each="board : ${list}">';
                            //<td><a href='view.html?userid=" + userid + "'>" + userid + "</a></td>
                            html += '<td><a href= "/plandetail?planNo=' + data[i].planNo + '">' + data[i].travelTitle + '</a></td>';

                            html += '<td id="spot">' + data[i].spot + '</td>';
                            html += '<td id="startDate">' + data[i].startDate + '</td>';
                            html += '<td id="sign"> ~ </td>';
                            html += '<td id="arriveDate">' + data[i].arriveDate + '</td></tr>';

                        }
                        //}
                        $("#planList").empty();
                        $("tbody").html(html);

                    }
                });
            }

        },
        error: function (error) {
            console.log(error);

        }
    });


});