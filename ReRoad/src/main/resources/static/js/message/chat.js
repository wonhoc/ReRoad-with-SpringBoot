const url = 'http://localhost:8080';
let stompClient;
let username;
let newMessage = new Map();



$(document).ready(function() {
    let username = $('#username').val();

if(username != null){

    console.log("connecting to chat...")

    let socket = new SockJS(url + '/chat');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function (frame) {

        stompClient("topic/message/"+username, function (message){
            let data = JSON.parse(message.body);

            $('#alarm').append("<strong id='newMessage' style='color: red;'>new</strong>");
            render(data.message, data.fromLogin);
        });

    });


    function sendMsg(from, text) {
        console.log('from : ', from, 'text : ', text);
        console.log(`selectedUser : ${username}`);
        console.log(`stompClient : ${stompClient}`);

        stompClient.send('/app/chat/' + username, {}, JSON.stringify({
            message: text,
            fromLogin: from

        }));
    }

}});


