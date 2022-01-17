$(document).ready(function () {

    let length = $('#search').children().length

    let cityList = [];

    let selected = $('#selected').children('div').text().trim();



    for (var i = 0; i < length; i++) {
        const id = $('#search').find('span').eq(i).attr('id');
        cityList.push(id);
    }

    for (var i = 0; i < cityList.length; i++) {
        if (cityList[i] == selected) {
            $('#' + selected).hide();

        }
    }

    $(document).on('click', "#searchDomestic", function (){


        $('#search').show()
    })

    $(document).on('click', ".name", function (){

        var el = $('#searchDomestic');

        for(var i=0; i<el.length; i++){

            el[i].value = '';
        }

        let tumb = '<img style="margin-left: 250px;\n' +
            '    margin-bottom: 100px;\n' +
            '    margin-top: 70px;\n' +
            '    border-radius: 50%;\n' +
            '    border: 3px solid teal;\n' +
            '    width: 300px;\n' +
            '    height: 300px;" class = "thumnail" src="/images/noImage.png">';
        $(".thumb").html(tumb);

        let region = $(this).text();

        let currentRegion = $('#domesticName').val();

        $('#'+region).hide();

        $('#selected').html("");

        let str = '<div id="currentDomestic">' + region +'</div>';
            str += '<input type="hidden" name = "domesticName" id = "domesticName" value="'+ region +'">';

        $('#selected').html(str);
        $('#'+currentRegion).show();

        let length = $('#search').children().length

        for (let i = 0 ; i<length ; i++){
            const id = $('#search').find('span').eq(i).attr('id');
                cityList.push(id);

        }

        for (var i = 0; i < cityList.length; i++) {

            if(cityList[i] != region){
                $('#'+cityList[i]).show()
            }


        }

        $('#search').hide();

    })

    $('#searchDomestic').on("keyup", function () {

        let searchKeyword = $(this).val().trim();

        $(".name").filter(function () {
            $(this).toggle($(this).text().trim().indexOf(searchKeyword) > -1)
        });


    });

    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function(e) {
                $('#imgArea').attr('src', e.target.result);
            }
            $('.thumnail').hide()
            reader.readAsDataURL(input.files[0]);
        }
    }

    $(":input[name='boardFileInput']").change(function() {
        if( $(":input[name='boardFileInput']").val() == '' ) {
            $('#imgArea').attr('src' , '');
            $('.thumnail').hide()
        }
        $('.thumnail').hide()
        $('#imgViewArea').css({ 'display' : '' });
        readURL(this);
    });

    // 이미지 에러 시 미리보기영역 미노출
    function imgAreaError(){
        $('#imgViewArea').css({ 'display' : 'none' });
    }

});