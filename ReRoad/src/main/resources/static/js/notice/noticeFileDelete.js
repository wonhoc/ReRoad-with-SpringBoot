$(document).ready(function() {
    $(document)
        .on(
            "click",
            "#removeFileBtn",
            function () {
                const fileNo = $(this).parents(
                    'tr').attr('id');
                const noticeNo = $('#noticeNo').val();

                $
                    .ajax({
                        url: '/removeNoticeFile/'
                            + fileNo + "/" + noticeNo ,
                        type: 'DELETE',
                        dataType: 'json',
                        success: function (data) {
                            $("#originFileList").remove();
                            console.log(data[0].fileOrigin);
                            let html = '<tbody id="originFileList">';
                            for(let i = 0; i<data.length; i++) {
                                html += '<tr id=' + data[i].fileNo + '>';
                                html += '<td>' + data[i].fileOrigin + '</td>';
                                html += '<td>' + data[i].fileSize + 'bytes</td>';
                                html += '<td><button type="button"  id="removeFileBtn">파일 삭제</button></td></tr>'

                            }
                           html += '</tbody>';
                            $("#files").html(html);
                            },
                        error: function (err) {
                            console.log(err);
                        }

                    });
            });
});