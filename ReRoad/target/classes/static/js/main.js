$(document).ready(function(){
	
	var vehicl = "열차";
	var tripType ="편도";
	
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
	
		$('#hideOnbush').show();
	
	});
	
	$('#hideOnbush').click(function(){
		$('#hideOnbush').hide()
	});
	

});