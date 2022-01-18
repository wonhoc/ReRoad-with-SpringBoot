$(document).ready(function(){

	//페이징 처리를 위한 전역변수 선언
	const depLo = $('#depLo').val();   //출발지
	const arrLo = $('#arrLo').val();   //도착지
	const depTime = $('#startDate').val();   //출발시간
	const arrTime = $('#arrDate').val();   //돌아오는시간
	const depLoName = $('#depLoName').val();
	const arrLoName = $('#arrLoName').val();
	const vehiclType = $('#vehiclType').val();

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


	//가는 페이징 처리
	$(document).on('click', '.page', function(){

		//vehiclType에 따라서 url 달라지게 하기
		let ajaxUrl = '';
		let data;

		//열차일때
		if(vehiclType == 'train'){
			ajaxUrl = '/getTrainSchedule';

			data = JSON.stringify({
				"depPlaceId" : depLo,
				"arrPlaceId" : arrLo,
				"pageNo" : $(this).text(),
				"depPlandTime" : depTime
			});

		}else{
			//버스일때
			ajaxUrl = '/getExpBusSchedule';

			data = JSON.stringify({
				"depTerminalId" : depLo,
				"arrTerminalId" : arrLo,
				"pageNo" : $(this).text(),
				"depPlandTime" : depTime
			});
		}//if end

		//ajax통신
		$.ajax({
			url : ajaxUrl,
			method : 'POST',
			dataType : 'json',
			contentType: 'application/json;charset=utf-8',
			data: data,
			success: function(data){

				//응답한 객체
				let scList = data.scList.scList;
				let scLists = data.scList;

				console.log('scList : ' + scList);
				console.log('scList.length : ' + scList.length);

				//ajax통신에 성공했을시
				//scBox 비우기
				$('#scBox').html('');

				let html = '';

				//열차일때
				if(vehiclType == 'train'){
					for(let i = 0; i < scList.length; i++){

						html += '<div class="scInfo">';

						html += '<div class="scInfoEl timeAndAw">';
						html += '<span class="time">' + timeSub(scList[i].depplandtime) + '</span>';
						html += '<i class="fas fa-arrow-right fa-3x"></i>';
						html += '<span class="time">' + timeSub(scList[i].arrplandtime) + '</span>';
						html += '<div id="place"><div id="infoDepLoName">' + depLoName + '</div>'
						html += '<div id="infoArrLoName">' + arrLoName + '</div></div>';
						html += '</div>';

						html += '<div class="scInfoEl spanTimeBox">';
						html += '<i class="far fa-clock"></i>';
						html += '<span class="spanTime">' + scList[i].spenTime + '</span>';
						html += '</div>';

						html += '<div class="scInfoEl trainTypeNoBox">';
						html += '<div class="trainType">' + scList[i].traingradename + '</div>';
						html += '<div class="trainNo">' + scList[i].trainno + '</div>';
						html += '</div>';

						html += '<div class="scInfoEl chargeBox">';
						//운임
						if(scList[i].adultcharge != '0'){

							html += '<span>' + formatComa(scList[i].adultcharge) + '</span><span class="won">원</span>';
						}else{

							html += '</span><span class="won">운임 책정 중</span>';
						}//if end
						html += '</div>';

						html += '</div>';

					}//for end

					//페이징 처리
					let totalPage = (parseInt(scLists.totalCnt) / parseInt(scLists.numOfRows)) + 1;

					for(let i = 1; i <= totalPage; i++){

						//현제 페이지 번호 표시
						if(parseInt(scLists.pageNo) == i){
							html += '<span class="cPage">' + i + '</span>';
						}else{
							html += '<span class="page">' + i + '</span>';
						}//if end
					}//for end

				}else{
					//버스일때

					for(let i = 0; i < scList.length; i++){

						html += '<div class="scInfo">';

						html += '<div class="scInfoEl timeAndAw">';
						html += '<span class="time">' + timeSub(scList[i].depPlandTime) + '</span>';
						html += '<i class="fas fa-arrow-right fa-3x"></i>';
						html += '<span class="time">' + timeSub(scList[i].arrPlandTime) + '</span>';
						html += '<div id="place"><div id="infoDepLoName">' + depLoName + '</div>'
						html += '<div id="infoArrLoName">' + arrLoName + '</div></div>';
						html += '</div>';

						html += '<div class="scInfoEl spanTimeBox">';
						html += '<i class="far fa-clock"></i>';
						html += '<span class="spanTime">' + scList[i].spanTime + '</span>';
						html += '</div>';

						html += '<div class="scInfoEl trainTypeNoBox">';
						html += '<div class="trainType">' + scList[i].gradeNm + '</div>';
						html += '</div>';

						html += '<div class="scInfoEl chargeBox">';

						//운임
						if(scList[i].charge != '0'){

							html += '<span>' + formatComa(scList[i].charge) + '</span><span class="won">원</span>';
						}else{

							html += '</span><span class="won">운임 책정 중</span>';
						}//if end

						html += '</div>';

						html += '</div>';

					}//for end

					//페이징 처리
					let totalPage = (parseInt(scLists.totalCnt) / parseInt(scLists.numOfRows)) + 1;

					for(let i = 1; i <= totalPage; i++){

						//현제 페이지 번호 표시
						if(parseInt(scLists.pageNo) == i){
							html += '<span class="cPage">' + i + '</span>';
						}else{
							html += '<span class="page">' + i + '</span>';
						}//if end
					}//for end



				}//if end

				$('#scBox').html(html);

			}//success() end

		});//ajax() end

	});//end


//오는 페이징 처리, 가는 페지징 처리와 같지만 오는곳과 가는곳을 바꿔서 적용.
	$(document).on('click', '.tpage', function(){

		//vehiclType에 따라서 url 달라지게 하기
		let ajaxUrl = '';
		let data;

		//열차일때
		if(vehiclType == 'train'){
			ajaxUrl = '/getTrainSchedule';

			data = JSON.stringify({
				"depPlaceId" : arrLo,
				"arrPlaceId" : depLo,
				"pageNo" : $(this).text(),
				"depPlandTime" : arrTime
			});

		}else{
			//버스일때
			ajaxUrl = '/getExpBusSchedule';

			data = JSON.stringify({
				"depTerminalId" : arrLo,
				"arrTerminalId" : depLo,
				"pageNo" : $(this).text(),
				"depPlandTime" : arrTime
			});
		}//if end

		//ajax통신
		$.ajax({
			url : ajaxUrl,
			method : 'POST',
			dataType : 'json',
			contentType: 'application/json;charset=utf-8',
			data: data,
			success: function(data){

				//응답한 객체
				let scList = data.scList.scList;
				let scLists = data.scList;
				//console.log(scList[0].depplandtime);

				//ajax통신에 성공했을시
				//scBox 비우기
				$('#arrScBox').html('');

				let html = '';

				//열차일때
				if(vehiclType == 'train'){
					for(let i = 0; i < scList.length; i++){

						html += '<div class="scInfo">';

						html += '<div class="scInfoEl timeAndAw">';
						html += '<span class="time">' + timeSub(scList[i].depplandtime) + '</span>';
						html += '<i class="fas fa-arrow-right fa-3x"></i>';
						html += '<span class="time">' + timeSub(scList[i].arrplandtime) + '</span>';
						html += '<div id="place"><div id="infoDepLoName">' + arrLoName + '</div>'
						html += '<div id="infoArrLoName">' + depLoName + '</div></div>';
						html += '</div>';

						html += '<div class="scInfoEl spanTimeBox">';
						html += '<i class="far fa-clock"></i>';
						html += '<span class="spanTime">' + scList[i].spenTime + '</span>';
						html += '</div>';

						html += '<div class="scInfoEl trainTypeNoBox">';
						html += '<div class="trainType">' + scList[i].traingradename + '</div>';
						html += '<div class="trainNo">' + scList[i].trainno + '</div>';
						html += '</div>';

						html += '<div class="scInfoEl chargeBox">';

						//운임
						if(scList[i].adultcharge != '0'){

							html += '<span>' + formatComa(scList[i].adultcharge) + '</span><span class="won">원</span>';
						}else{

							html += '</span><span class="won">운임 책정 중</span>';
						}//if end

						html += '</div>';

						html += '</div>';

					}//for end

					//페이징 처리
					let totalPage = (parseInt(scLists.totalCnt) / parseInt(scLists.numOfRows)) + 1;

					for(let i = 1; i <= totalPage; i++){

						//현제 페이지 번호 표시
						if(parseInt(scLists.pageNo) == i){
							html += '<span class="tcPage">' + i + '</span>';
						}else{
							html += '<span class="tpage">' + i + '</span>';
						}//if end
					}//for end

				}else{
					//버스일때

					for(let i = 0; i < scList.length; i++){

						html += '<div class="scInfo">';

						html += '<div class="scInfoEl timeAndAw">';
						html += '<span class="time">' + timeSub(scList[i].depPlandTime) + '</span>';
						html += '<i class="fas fa-arrow-right fa-3x"></i>';
						html += '<span class="time">' + timeSub(scList[i].arrPlandTime) + '</span>';
						html += '<div id="place"><div id="infoDepLoName">' + arrLoName + '</div>'
						html += '<div id="infoArrLoName">' + depLoName + '</div></div>';
						html += '</div>';

						html += '<div class="scInfoEl spanTimeBox">';
						html += '<i class="far fa-clock"></i>';
						html += '<span class="spanTime">' + scList[i].spanTime + '</span>';
						html += '</div>';

						html += '<div class="scInfoEl trainTypeNoBox">';
						html += '<div class="trainType">' + scList[i].gradeNm + '</div>';
						html += '</div>';

						html += '<div class="scInfoEl chargeBox">';

						//운임
						if(scList[i].charge != '0'){

							html += '<span>' + formatComa(scList[i].charge) + '</span><span class="won">원</span>';
						}else{

							html += '</span><span class="won">운임 책정 중</span>';
						}//if end

						html += '</div>';

						html += '</div>';

					}//for end

					//페이징 처리
					let totalPage = (parseInt(scLists.totalCnt) / parseInt(scLists.numOfRows)) + 1;

					for(let i = 1; i <= totalPage; i++){

						//현제 페이지 번호 표시
						if(parseInt(scLists.pageNo) == i){
							html += '<span class="tcPage">' + i + '</span>';
						}else{
							html += '<span class="tpage">' + i + '</span>';
						}//if end
					}//for end



				}//if end

				$('#arrScBox').html(html);

			}//success() end

		});//ajax() end

	});








});//ready() end



//시간 자르기
function timeSub(time){

	console.log('time : ' + time);

	let hh = time.substring(8, 10);
	let mm = time.substring(10, 12);

	console.log(hh + ':' + mm);

	return hh + ':' + mm;

}//timeSub() end

//천원단위 콤마
function formatComa(charge){

	return charge.replace(/\B(?=(\d{3})+(?!\d))/g, ",");

}//formatComa() end