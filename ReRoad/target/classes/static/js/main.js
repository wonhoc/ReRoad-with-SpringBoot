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
		//#vehiclType에 value 추가
		vehicl == "열차" ? $('#vehiclType').val('train') : $('#vehiclType').val('expBus');
		console.log($('#vehiclType').val());
		
		
		
	});
	//편도, 왕복선택
	$('.tripType').click(function(){	
		$(this).css('background-color', 'rgb(233,233,233);');
		$(this).prevAll().css('background-color', 'teal');
		$(this).nextAll().css('background-color', 'teal');
		
		tripType = $(this).children().text().trim();
		
		//날짜선택 변경
		if(tripType == "편도"){
		
			$('#date').attr('class', 'searchBody');
			$('#date').children('#bodyDate').text('날짜');
			$('#date').children('#startDate').val('');
			$('#dateArr').children('#arrDate').val('none');
			$('#dateArr').hide();
			
		}//if end
		
		if(tripType == "왕복"){
	
			$('#date').attr('class', 'searchBodyDate');
			$('#date').children('#bodyDate').text('가는날');
			$('#date').children('#startDate').val('');
			$('#dateArr').show();
		
		}//if end
		
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
				$('#hideOnbush').hide(); 
		}//if end
	});
	
	//선택창 숨기기
	$('.listWrapper').click(function(){
		$('#hideOnbush').hide();
	});
	
	//역 선택 보여주기
	$('.stNameIn').click(function(){	
		tripSel.children('.bodyTextdiv').text($(this).text());
		tripSel.children('.selLoNa-me').val($(this).text().trim());
		tripSel.children('.selLo').val($(this).attr('value'));
		tripSel.children('.selLoName').val($(this).text());
	});
	
	//검색어 입력
	$("#searchInput").on("keyup", function() {
	    let searchKeyword = $(this).val().trim();
	    $(".stNameIn").filter(function() {
	      $(this).toggle($(this).text().trim().indexOf(searchKeyword) > -1)
	    });
  });
	
	
	//검색버튼 클릭시 검사
	$('#btnSearch').click(function(){
	
		let flag = true;
		let letDepLo = $('#depLo').val();
		let letArrLo = $('#arrLo').val();
		let letStartDate = $('#startDate').val();
		let letArrDate = $('#arrDate').val();
		let letDepName = $('#arr').children('.bodyTextdiv').text().trim();
		let letArrName =  $('#dep').children('.bodyTextdiv').text().trim();
		let hideTime = 3000;	//숨김처리시간
		
		//출, 도착지 같은지 비교
		if(letDepName == letArrName){	
			flag = false;
			$('#btnSearch').popover("enable");
			$('#btnSearch').removeAttr("data-content");
			$('#btnSearch').attr("data-content","출발지와 도착지를 모두 '" + letDepName +"'을 선택하셨습니다. 출발지와 도착지를 다르게 선택해주세요!");	
			$('#btnSearch').popover("show");
			$('#btnSearch').popover("disable");
			 setTimeout(function() {$('#btnSearch').popover('hide');}, hideTime);
		}//if end
		
		//출발지 선택
		if(letDepLo == "") {
			flag = false;
			$('#bodyDep').attr("data-content","출발지를 선택 해주세요.");	
			$('#bodyDep').popover("show");
			setTimeout(function() {$('#bodyDep').popover('hide');}, hideTime);	//delay 옵션은 이벤트로 적용되지 않으니 setTimeout으로 숨겨주기
		}//if end
		
		//도착지 선택
		if(letArrLo == "") {
			flag = false;
			$('#bodyArr').attr("data-content","도착지를 선택 해주세요.");
			$('#bodyArr').popover("show");
			setTimeout(function() {$('#bodyArr').popover('hide');}, hideTime);
		}//if end
		
		//출발일 선택
		if(letStartDate == "") {
			flag = false;
			$('#bodyDate').attr("data-content","날짜를 선택 해주세요.");
			$('#bodyDate').popover("show");
			setTimeout(function() {$('#bodyDate').popover('hide');}, hideTime);
		}//if end
		
		//오는날 선택
		if($('#dateArr').css('display') == 'block' && letArrDate == "none") {
			flag = false;
			$('#bodyDateArr').attr("data-content","오는날을 선택 해주세요.");
			$('#bodyDateArr').popover("show");
			setTimeout(function() {$('#bodyDateArr').popover('hide');}, hideTime);
		}//if end
		
		
	
		//submit?
		if(flag){
			$('#ScSelectForm').submit();
		}//if end
	});
	
	
	
	//날짜 입력
	$('#date').click(function(){
	
		$('#inputDatepicker').datepicker({
			dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'],
			monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
			monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
			dateFormat : 'yymmdd',
			showButtonPanel: true,
			showMonthAfterYear : true,
			closeText: '닫기',		
			onSelect : function(){
				//선택시 선택창의 값이 바뀐다
				let yymmdd = $('#inputDatepicker').val();
				$('#bodyDate').text(dateCon(yymmdd));
				$('#bodyDate').css('font-size', '25px');
				$('#startDate').val(yymmdd);
			}
		});
		$('#inputDatepicker').show().focus().hide();	
	});
	
	//오는날 입력
	$('#dateArr').click(function(){
	
		$('#inputDatepickerArr').datepicker({
			dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'],
			monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
			monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
			dateFormat : 'yymmdd',
			showButtonPanel: true,
			showMonthAfterYear : true,
			closeText: '닫기',		
			onSelect : function(){
				//선택시 선택창의 값이 바뀐다
				let yymmdd = $('#inputDatepickerArr').val();
				$('#bodyDateArr').text(dateCon(yymmdd));
				$('#bodyDateArr').css('font-size', '25px');
				$('#arrDate').val(yymmdd);
			}
		});
		$('#inputDatepickerArr').show().focus().hide();	
	});
	
	
	
	
});//ready() end




//날짜 포멧 변환
function dateCon(date){
	
	let mon = date.substr(4,2);
	let day = date.substr(6,2);
	let ymd = mon + '월' + day + '일';
	
	return ymd;
	
}//dateCon() end