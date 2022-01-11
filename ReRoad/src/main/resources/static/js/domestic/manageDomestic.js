$(document).ready(function () {

    let length = $('#search').children().length

    let cityList = [];

    let selectedLength = $('#entire').children().length;

    let selectedList = [];


    for (var i = 0; i < length; i++) {
        const id = $('#search').children().eq(i).attr('id');
        cityList.push(id);
    }

    for (var i = 0; i < selectedLength; i++) {
        const id = $('#entire').children().eq(i).attr('id');
        selectedList.push(id);

    }

    for (var i = 0; i < cityList.length; i++) {
        for (var j = 0; j < selectedList.length; j++) {
            if (cityList[i] == "whole" + selectedList[j]) {
                $('#' + cityList[i]).remove();

            }
        }
    }


    $('#domesticname').on("click", function () {

        $('#search').show()
        list = $(this);

    });


    $('.entireDomesticName').click(function () {
        let temp = $(this).children('.name').text();

    });


    $(document).on('click', '.entireDomesticName', function () {
        $('#search').hide()
    })


    $('#domesticname').on("keyup", function () {

        let searchKeyword = $(this).val().trim();

        $(".name").filter(function () {
            $(this).toggle($(this).text().trim().indexOf(searchKeyword) > -1)
        });


    });

    $(document).on('click', '.name', function () {

        let number = $('#entire').children().length;

        let region = $(this).text();

        $('#whole' + region).remove();


        if (number > 5) {
            alert("6개 이상입니다.")
        } else {
            list = '<div id="' + $(this).text() + '"><input type="checkbox" name="domesticName" value="' + $(this).text() + '">' + $(this).text() + '</div>';
            $('#entire').append(list);
        }

        $('#search').hide()


    });

    $('#removeList').on('click', function () {


        var arr = [];


        $("input:checkbox[name='domesticName']:checked").each(function () {
            const id = $(this).parent().attr('id');
            arr.push(id);

        });


        for (var i = 0; i < arr.length; i++) {
            $('#' + arr[i]).remove();

            let html = '<div id="whole' + arr[i] + '" className="entireDomesticName">' +
                '<span class="name">' + arr[i] + '</span></div>';

            $('#search').append(html);

        }

    });

    $(document).on('click', '.maincity',function (){
       let citytext = $(this).text().trim();





    })


});