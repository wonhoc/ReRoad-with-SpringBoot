
function dateFormat(date) {
  let hour = date.getHours();
  let minute = date.getMinutes();
  let second = date.getSeconds();

  hour = hour >= 10 ? hour : '0' + hour;
  minute = minute >= 10 ? minute : '0' + minute;
  second = second >= 10 ? second : '0' + second;

  return  hour + ':' + minute + ':' + second;
}
$(document).ready(function () {

  $(document).on('keydown', '#chat-message-input', function (key) {

    //키의 코드가 13번일 경우 (13번은 엔터키)
    if (key.keyCode == 13) {
      //ID가 alpreah_btn을 찾아 클릭해준다.
      //버튼 말고도 p태그나 다른 태그도 다 응용 가능 합니다.
      //대신 이벤트 발생을 위해서는 29번쨰 줄 코드처럼 이벤트를 걸어줘야 합니다.
      $("#btnSend").click();
    }
  });

  $(document).on('click', '#chat-message-input', function () {
    $('#isValid').css('color', '#434651');
    $('#btnSend').attr("disabled", false);
  });
  $(document).on('keydown', '#chat-message-input', function (key) {
    if(key.keyCode != 13){
      $('#isValid').css('color', '#434651');
      $('#btnSend').attr("disabled", false);
    }
  });



  /*$(function () {*/
  var ChatManager = (function () {
    function ChatManager() {
    }

    // const othername = $('#othername').val();
    const myname = $('#myname').val();
    ChatManager.textarea = $('#chat-content');
    ChatManager.socket = null;
    ChatManager.stompClient = null;
    ChatManager.sessionId = null;
    ChatManager.chatRoomId = null;
    ChatManager.joinInterval = null;
    ChatManager.username = null;
    //   ChatManager.othername = othername;

    ChatManager.join = function () {
      $.ajax({
        url: 'join',
        headers: {
          "Content-Type": "application/json"
        },
        /* beforeSend: function () {
           $('#btnJoin').text('Cancel');
           ChatManager.updateText('waiting'+ othername, false);
           ChatManager.joinInterval = setInterval(function () {
             ChatManager.updateText('.', true);
           }, 1000);
         },*/
        success: function (chatResponse) {
          console.log('Success to receive join result. \n', chatResponse);
          if (!chatResponse) {
            return;
          }

          clearInterval(ChatManager.joinInterval);
          if (chatResponse.responseResult == 'SUCCESS') {
            ChatManager.sessionId = chatResponse.sessionId;
            ChatManager.chatRoomId = chatResponse.chatRoomId;
            ChatManager.updateTemplate('chat');
            ChatManager.updateText('>> 채팅방에 입장하셨습니다 :)\n', false);
            ChatManager.connectAndSubscribe();
          } else if (chatResponse.responseResult == 'CANCEL') {
            ChatManager.updateText('>> Success to cancel', false);
            $('#btnJoin').text('Join');
          } else if (chatResponse.responseResult == 'TIMEOUT') {
            ChatManager.updateText('>> Can`t find user :(', false);
            $('#btnJoin').text('Join');
          }
        },
        error: function (jqxhr) {
          clearInterval(ChatManager.joinInterval);
          if (jqxhr.status == 503) {
            ChatManager.updateText('\n>>> Failed to connect:(\nPlz try again', true);
          } else {
            ChatManager.updateText(jqxhr, true);
          }
          console.log(jqxhr);
        },
        complete: function () {
          clearInterval(ChatManager.joinInterval);
        }
      })
    };

    ChatManager.cancel = function () {
      $.ajax({
        url: 'cancel',
        headers: {
          "Content-Type": "application/json"
        },
        success: function () {
          ChatManager.updateText('', false);
        },
        error: function (jqxhr) {
          console.log(jqxhr);
          alert('Error occur. please refresh');
        },
        complete: function () {
          clearInterval(ChatManager.joinInterval);
        }
      })
    };

    ChatManager.connectAndSubscribe = function () {
      if (ChatManager.stompClient == null || !ChatManager.stompClient.connected) {
        var socket = new SockJS('/chat-websocket');
        ChatManager.stompClient = Stomp.over(socket);
        ChatManager.stompClient.connect({chatRoomId: ChatManager.chatRoomId}, function (frame) {
          console.log('Connected: ' + frame);
          ChatManager.subscribeMessage();
        });
      } else {
        ChatManager.subscribeMessage();
      }
    };

    ChatManager.disconnect = function () {
      if (ChatManager.stompClient !== null) {
        ChatManager.stompClient.disconnect();
        ChatManager.stompClient = null;
        ChatManager.updateTemplate('wait');
      }
    };

    ChatManager.sendMessage = function () {
      console.log('Check.. >>\n', ChatManager.stompClient);
      console.log('send message.. >> ');
      var $chatTarget = $('#chat-message-input');
      var message = $chatTarget.val();
      $chatTarget.val('');

      var payload = {
        messageType: 'CHAT',
        senderSessionId: ChatManager.sessionId,
        message: message,
        username: myname
      };

      ChatManager.stompClient.send('/app/chat.message/' + ChatManager.chatRoomId/* + '/' + othername*/, {}, JSON.stringify(payload));
    };

    ChatManager.subscribeMessage = function () {
      ChatManager.stompClient.subscribe('/topic/chat/' + ChatManager.chatRoomId /*+ '/' + myname*/, function (resultObj) {
        console.log('>> success to receive message\n', resultObj.body);
        var result = JSON.parse(resultObj.body);
        var message = '';
        let today = new Date();
        let sendTime = dateFormat(today);


        if (result.messageType == 'CHAT') {
          if (result.senderSessionId === ChatManager.sessionId) {
            message += '[나](' +sendTime+') : ';
          } else {
            message += '[' + result.username + '](' +sendTime+') : ';
          }
          message += result.message + '\n';
        } else if (result.messageType == 'DISCONNECTED') {
          const chaturl = 'http://localhost:8080';
          const disconName = result.username;
          message = '>> ' + disconName + ' 님이 퇴장하셨습니다 :(\n';
          $.ajax({
            url: chaturl + "/disconnect/" + result.username,
            success: function (response) {
              console.log(`response : ${response}`);
            }
            ,
            error: function (ex) {
              console.log(`ex : ${ex}`);

            }
          });

          //ChatManager.disconnect();
        } else if (result.messageType == 'CONNECTED') {
          message = '>> ' + result.username + ' 님이 채팅에 입장하셨습니다 :)\n';

        }
        ChatManager.updateText(message, true);
      });
    };

    ChatManager.updateTemplate = function (type) {
      var source;
      if (type == 'wait') {
        source = $('#wait-chat-template').html();
      } else if (type == 'chat') {
        source = $('#send-chat-template').html();
      } else {
        console.log('invalid type : ' + type);
        return;
      }
      var template = Handlebars.compile(source);
      var $target = $('#chat-action-div');
      $target.empty();
      $target.append(template({}));
    };

    ChatManager.updateText = function (message, append) {
      if (append) {
        ChatManager.textarea.val(ChatManager.textarea.val() + message);
      } else {
        ChatManager.textarea.val(message);

      }
    };

    return ChatManager;
  }());

  $(document).on('click', '#btnJoin', function () {

    let username = $('#myname').val();
    const chaturl = 'http://localhost:8080';

    $.ajax({
      url: chaturl + "/registration/" + username,
      success: function (response) {
        console.log(`response : ${response}`);

        ChatManager.join();
      }

      ,
      error: function (ex) {
        console.log(`ex : ${ex}`);
        if (ex.status === 400) {
          alert("Login is already busy!")
        }
      }
    });


  });

  $(document).on('click', '#btnSend', function () {
    const msg = $('#chat-message-input').val();
    var regex = / /gi;
    const top = $('#chat-content').prop('scrollHeight');

    if (msg == "" || msg.replace(regex, '') == ""){
      $('#isValid').text('채팅 내용을 입력하세요');
      $('#isValid').css('color', 'red');
      $('#btnSend').attr("disabled", true);

    } else {
      $('#isValid').css('color', '#434651');
      $('#btnSend').attr("disabled", false);
      ChatManager.sendMessage();
      $('#chat-content').scrollTop(top);
    }
  });




  ChatManager.updateTemplate('wait');
});