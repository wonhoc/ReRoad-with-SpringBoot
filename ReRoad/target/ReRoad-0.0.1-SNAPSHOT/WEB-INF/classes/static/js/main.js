$(document).ready(function(){
	
	var vehicl = "열차";
	var tripType ="편도";
	var tripSel;
	
	//열차, 버스 선택
	$('.searchBoxHead').click(function(){	
		$(this).css('background-color', 'rgb(242,242,242)');
		$(this).prevAll().css('background-color', 'teal');
		$(this).nextAll().css('background-color', 'teal');
		
		vehicl = $(this).text();
		console.log(vehicl);
		
	});
	//편도, 왕복선택
	$('.tripType').click(function(){	
		$(this).css('background-color', 'rgb(242,242,242)');
		$(this).prevAll().css('background-color', 'teal');
		$(this).nextAll().css('background-color', 'teal');
		
		tripType = $(this).children().text();
		console.log(tripType);
		
	});
	
	//출발지 선택
	$('#dep').click(function(){
	
		if ($('#hideOnbush').css('display') === 'none' ){ 
			$('#hideOnbush').show();
			tripSel = $(this);
		}else {
			$('#hideOnbush').hide(); }//if end
	});
	
	//도착지 선택
	$('#arr').click(function(){
	
		if ($('#hideOnbush').css('display') === 'none' ){ 
				$('#hideOnbush').show();
				tripSel = $(this);
			}else {
				$('#hideOnbush').hide(); }//if end
	});
	
	//선택창 숨기기
	$('.listWrapper').click(function(){
		$('#hideOnbush').hide();
	});
	
	//역 선택 보여주기
	$('.stNameIn').click(function(){	
		tripSel.children('.bodyTextdiv').text($(this).text());
	});
	
	//검색어 입력
	$("#searchInput").on("keyup", function() {
	    let searchKeyword = $(this).val().trim();
	    $(".stNameIn").filter(function() {
	      $(this).toggle($(this).text().trim().indexOf(searchKeyword) > -1)
	    });
  });
	
	
	//검색버튼 클릭시
	$('#btnSearch').click(function(){
		
		if($('#arr').children('.bodyTextdiv').text() == $('#dep').children('.bodyTextdiv').text()){
			alert('출발지와 도착지가 서로 같습니다.');
		}
	});
	
	//날짜 입력
	$('#date').click(function(){
		$('#date').datepicker();
		
	});
	
	
	
	
});