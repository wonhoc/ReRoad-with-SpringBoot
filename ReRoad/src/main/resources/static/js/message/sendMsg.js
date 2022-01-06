let $chatHistory;
let $button;
let $textarea;
let $chatHistoryList;

    $('#addComBtn').on('click',function (){


        function sendMessage(message) {
            let username = $('#username').val();//보내는 사람
            console.log(username)
            // **************************************************************
            sendMsg(username, message);

            if (message.trim() !== '') {
                var context = {
                    messageOutput: message,
                    toUserName: $('#userId').val()
                };

            }
        }



});