$(document).on('click', '#removeFileBtn', function (){

    var arr = [];
    $("input:checkbox[name='origintr']:checked").each(function () {
        const id = $(this).parent().parent().attr('id');
        arr.push(id);
    });



    for (var i = 0; i < arr.length; i++) {
        var noticefile = arr[i];
        $('#'+noticefile).remove();

        let html = '<input type="hidden" id="fileNo" name="fileNo" value="' + arr[i] + '">';
        $('#hiddenRoom').append(html);
    }

});