$(document).ready(function () {
    let board = document.getElementById('board_no');
    if(board == null) {
        $("#plus_btn").attr("disabled", true);
        $("#link_btn").attr('href', "#");

    }else {
        $("#plus_btn").attr("disabled", false);
    }
});