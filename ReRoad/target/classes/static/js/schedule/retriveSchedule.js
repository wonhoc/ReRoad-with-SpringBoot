$(document).ready(function(){
	
	//가는 스케줄 접기
	$('#foldStartSc').click(function(){
		
		if($('#scBox').css('display') != 'none'){		
			$('#scBox').hide();
			$('#unFoldStartSc').show();
			$(this).hide();
		}//if end
	
	});
	
	//가는 스케줄 펴기
	$('#unFoldStartSc').click(function(){
		
		if($('#scBox').css('display') == 'none'){
			$('#scBox').show();
			$('#foldStartSc').show();
			$(this).hide();
		}//if end
	
	});
	
	
	//오는 스케줄 접기
	$('#foldArrSc').click(function(){
		
		if($('#arrScBox').css('display') != 'none'){		
			$('#arrScBox').hide();
			$('#unFoldArrSc').show();
			$(this).hide();
		}//if end
	
	});
	
	//오는 스케줄 펴기
	$('#unFoldArrSc').click(function(){
		
		if($('#arrScBox').css('display') == 'none'){
			$('#arrScBox').show();
			$('#foldArrSc').show();
			$(this).hide();
		}//if end
	
	});
	
});