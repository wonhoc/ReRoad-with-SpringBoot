$(document).ready(function (){

    $.ajax({
        url: '/boardList',
        method: 'GET',
        dataType: 'json',
        success: function(data){
           const boardList = data.results;
            let str = "";
            $("#boardHtml").html("");
           for(let i = 0 ; i < boardList.length ; i++){
            str +='<table id="board">' +
                '<tr>' +
                    '<td colspan="2" rowspan="2"><img src="/images/tavlePlanner.jpg" id="img" class="cardThumnail"></td>' +
                    '<td id="boardTitle" colspan="4" rowspan="1">'+ boardList[i].boardTitle +'</td>' +
                '</tr>' +
                '<tr>' +
                    '<td colspan="2"><img  class="images" src="/images/board/user.png">'+ boardList[i].userNick + '</td>' +
                    '<td><img class="images" src="/images/board/commentCount.png">'+boardList[i].commentCount + '</td>' +
                    '<td><img class="images" src="/images/board/view.png">'+ boardList[i].boardCount +'</td>' +
                 '</tr>' +
                  '<tr>' +
                     '<td  colspan="6" height="100px;" >'+boardList[i].boardContent+'</td>'+
                  '</tr>' +
                 '</table>';
           }
            $("#boardHtml").html(str);
        },
        error: function(err){
            console.log(err);
        }
    });

    });
