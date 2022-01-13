$(document).ready(function () {
    let board = document.getElementById('board_no');
    if(board == null) {
        $("#plus_btn").attr("disabled", true);
        $("#link_btn").attr('href', "#");

    }else {
        $("#plus_btn").attr("disabled", false);
    }

    // let role = document.getElementById('role');
    // if(role == 'ROLE_ADMIN') {
    //     $("#list_btn").show();
    // }else {
    //     $("#list_btn").hide();
    // }
});