$(document).ready(function() {
    let $chatHistory;
    let $textarea;
    let username = $('#username').val();
    let selectedUser = $('#userId').val();
    let boardNo = $('#boardNo').val();





    $('#addComBtn').on('click', function () {


        sendMessage($('#comContent').val());

        function sendMessage(message) {

            console.log(username);

            sendMsg(username, message, boardNo);

        }

        function sendMsg(from, text, boardNo) {

            console.log('from : ', from, 'text : ', text);
            console.log(`selectedUser : ${selectedUser}`);
            console.log(`stompClient : ${stompClient}`);

            stompClient.send('/app/chat/' + selectedUser, {}, JSON.stringify({
                message: text,
                fromLogin: from,
                boardNo : boardNo
            }));

        }


        function init() {
            cacheDOM();
            bindEvents();
        }

        function bindEvents() {
            $textarea.on('keyup', addMessageEnter.bind(this));
        }

        function cacheDOM() {
            $chatHistory = $('.chat-history');
            $textarea = $('#comContent');
        }


        function addMessageEnter(event) {
            // enter was pressed
            if (event.keyCode === 13) {
                addMessage();
            }
        }

        init();


    });

});