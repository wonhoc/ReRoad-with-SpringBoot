function notice_delete(){
    $.ajax({
        url : "/deleteNotice/" + noticeNo,
        type : "DELETE",
        success : function(){
            location.href = "noticeList.html";
        }
    });
}