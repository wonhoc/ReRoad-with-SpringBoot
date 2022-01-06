$(document).ready(function(){

	var VEHICLE = "";
	var tripType="";
	

	$('.searchBoxHead').click(function(){	
		$(this).css('background-color', 'rgb(242,242,242)');
		$(this).prevAll().css('background-color', 'teal');
		$(this).nextAll().css('background-color', 'teal');
		
		VEHICLE = $(this).text();
		console.log(VEHICLE);
		
	});
	
	$('.tripType').click(function(){	
		$(this).css('background-color', 'rgb(242,242,242)');
		$(this).prevAll().css('background-color', 'teal');
		$(this).nextAll().css('background-color', 'teal');
		
		tripType = $(this).children().text();
		console.log(tripType);
		
	});
	

});