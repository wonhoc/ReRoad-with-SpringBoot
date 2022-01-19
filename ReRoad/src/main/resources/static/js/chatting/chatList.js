function fetchAll() {
    const chaturl = 'ec2-13-125-26-218.ap-northeast-2.compute.amazonaws.com';
    const myname = $('#myname').val();
    $('#refresh').hide();
    $.get("/fetchAllUsers", function (response) {
        let users = response;
        let usersTemplateHTML = "<button id=\"hideuser\" onclick=\"hide()\">유저 목록 숨기기</button>";

       if(users.length == 0) {
           usersTemplateHTML = usersTemplateHTML  +
               '<li class="clearfix">' +
               '                  <div id="nobody"><h5 id="noone">이럴수가 ! 현재 채팅방에 아무도 없네요 :-(</h5></div>\n' +
               '            </li>';
           $('#usersList').html(usersTemplateHTML);
       }else {
           for (let i = 0; i < users.length; i++) {

               if (users[i] == myname) {
                   usersTemplateHTML = usersTemplateHTML +
                       '<li class="clearfix">' +
                       '                <img src="/images/chatting/chat' + (parseInt(i % 4) + 1) + '.png" width="20px" height="20px" />' +
                       '                    <div id="userNameAppender_' + users[i] + '" class="myname">' + users[i] + '(나)</div>\n' +
                       '            </li>';
               } else {
                   usersTemplateHTML = usersTemplateHTML +
                       '<li class="clearfix">' +
                       '                <img src="/images/chatting/chat' + (parseInt(i % 4) + 1) + '.png" width="20px" height="20px" />' +
                       '                    <div id="userNameAppender_' + users[i] + '" class="name">' + users[i] + '</div>\n' +
                       '            </li>';
               }
           }
           $('#usersList').html(usersTemplateHTML);
       }
    });
}

function hide() {
    $('#usersList').empty();
    $('#refresh').show();
}