const url = 'http://localhost:8080';
let stompClient;
let userId;
let newMessage = new Map();

function connectToChat(username){

// hand shaking
 let socket = new SockJs(url + '/chat');
 stompClient = Stomp.over(socket);


//Connect to the server

stompClient.connect({}, function (frame){

    console.log(`frame : ${frame}`);

    stompClient.subscribe("/topic/messages/" + username,function (message){

        let data =JSON.parse(message.body);
        alert('result : ' + (data.fromLogin == userId));

        render(data.message, data.fromLogin);

    });

});
}



function sendMsg(from, text){

    console.log('from : ', from, 'text : ', text);
    console.log(`selectedUser : ${userId}`);
    console.log(`stompClient : ${stompClient}`);

    stompClient.send('/app/chat/' + selectedUser, {}, JSON.stringify({
        message: text,
        fromLogin: from
    }));

}