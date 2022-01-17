$(document).ready(function() {
	
	//페이징처리
	 $.ajax({
        url : '/getBoardList',
        method: 'GET',
        dataType: 'json',
        success: function (data) {
            console.log(data);
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
                    let html = "";
                    let startRow = (page - 1) * 10 ;
                    let endRow = page * 10;
                    let totalCount = data.length
                    if (endRow > totalCount)
                    {
                        endRow = totalCount;
                    }
                    let totalPages = Math.ceil(totalCount/10);
                    if (totalCount%10 > 0) {
                        totalPages++;
                    }
                    let startPage = ((page - 1)/5) * 5 + 1;
                    let endPage = startPage + 5 - 1;
                    if(endPage > totalPages) {    //
                        endPage = totalPages;
                    }
                    if(data == null){
                    
                        html +=  '<table><tr><td>등록된 글이 없습니다.</td></tr>';
                        
                    }else{
                    
                        for (let i = startRow; i < endRow ; i++) {
							
							html += '<table id="boardTable">'
							
                            html += '<tr><td rowspan="3"><a href="/member/detailBoard/' + data[i].boardNo  + '">';
                           	//썸네일 보여주기
                           	if(data[i].boardFiles.length >= 1){
                           		//첫번째 파일의 이미지가 보여주게
                           		html += '<img src="/upload/' + data[i].boardFiles[0].systemFileName +'" class="thumnail"></a></td>';
                           	}else{
                           		//없다면 기본 이미지로 표시
                           		html += '<img src="/images/board/king.png" class="thumnail"></a></td>';
                           	}//if end
                           	//제목                       
                           	html += '<td width="210px;" id="title" colspan="3"><a href="/member/detailBoard/' + data[i].boardNo + '">';
                           	html += '<p>' + data[i].boardTitle + '</p></a></td></tr>';
                           	
                           	//닉네임
                           	html += '<tr id="Nick"><td colsapn="2"><img class="boardImg" src="/images/board/user.png"><span id="font">' + data[i].userNick + '</span></td>';
                           	html += '<td><img class="boardImg" src="/images/board/views.png"><span>' + data[i].boardCount + '</span></td></tr>';
                           	html += '<tr><td colspan="3">' + data[i].boardWdate + '</td></tr>';
                           	html += '<tr id="imgs"><td height="50px"; id="content" colspan="5">' + data[i].boardContent + '</td></tr>';
                           	
                           	
                           	html += '</table>';
                           	
                        }//for end

                        $("#noticeList").empty();
                        $("#html").html(html);
                    }
                }
            }); //end
        },
        error: function (error) {
            console.log(error);
        }
    });
	
	//paging end



    $(document).on('click', '#writeBtn', function(){

        location.href='/writeboardForm';

    });

	//검색
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
            	
            	let searchRst = data.results;
            	
            	
                $('#pagination-div').remove();
            	$('#pagination-div').twbsPagination('destroy');
            	
            	//검색된 결과가 없을때
            	if(searchRst.length == 0){
            	
            	$('#html').html("<table><tr><td>등록된 글이 없습니다.</td></tr>");
            	        
            	}else{
            	//검색된 결과가 있을 때
            	
            	 let htmlInit = "<div id='pagination-div2'>" + "<div id='page-content2'></div>" + "</div>";
                $("#paging").html(htmlInit);
            	
            	//새로운 페이징
            	$('#pagination-div2').twbsPagination({
            	
            	totalPages: Math.ceil(searchRst.length / 10),   // 총 페이지 번호 수
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
                    let html="";         
                    let startRow = (page - 1) * 10 ;
                    let endRow = page * 10;
                    let totalCount = searchRst.length;
                    if (endRow > totalCount)
                    {
                        endRow = totalCount;
                    }
                    
                    let totalPages = Math.ceil(totalCount/10);
                    if (totalCount%10 > 0) {
                        totalPages++;
                    }
                    
                    let startPage = ((page - 1)/5) * 5 + 1;
                    let endPage = startPage + 5 - 1;
                    if(endPage > totalPages) {    //
                        endPage = totalPages;
                    }
        
                    for (let i = startRow; i < endRow ; i++) {
						
						html += '<table id="boardTable">'
						
                        html += '<tr><td rowspan="3"><a href="/member/detailBoard/' + searchRst[i].boardNo  + '">';
                       	//썸네일 보여주기
                       	if(searchRst[i].boardFiles.length >= 1){
                       		//첫번째 파일의 이미지가 보여주게
                       		html += '<img src="/upload/' + searchRst[i].boardFiles[0].systemFileName +'" class="thumnail"></a></td>';
                       	}else{
                       		//없다면 기본 이미지로 표시
                       		html += '<img src="/images/board/king.png" class="thumnail"></a></td>';
                       	}//if end
                       	//제목                       
                       	html += '<td width="210px;" id="title" colspan="3"><a href="/member/detailBoard/' + searchRst[i].boardNo + '">';
                       	html += '<p>' + searchRst[i].boardTitle + '</p></a></td></tr>';
                       	
                       	//닉네임
                       	html += '<tr id="Nick"><td colsapn="2"><img class="boardImg" src="/images/board/user.png"><span id="font">' + searchRst[i].userNick + '</span></td>';
                       	html += '<td><img class="boardImg" src="/images/board/views.png"><span>' + searchRst[i].boardCount + '</span></td></tr>';
                       	html += '<tr><td colspan="3">' + searchRst[i].boardWdate + '</td></tr>';
                       	html += '<tr id="imgs"><td height="50px"; id="content" colspan="5">' + searchRst[i].boardContent + '</td></tr>';
                       	
                       	
                       	html += '</table>';
                       	
                    }//for end

                
                    $("#html").html(html);
                }
   
            	});
            	
            	
            	
            	
            	}//if end
            		
            },
            error: function (err) {
                alert(err);
            }
        });
    });


});