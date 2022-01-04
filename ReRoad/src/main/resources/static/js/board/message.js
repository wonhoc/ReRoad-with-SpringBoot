const url = 'http://localhost:8080';
let stompClient;
let selectedUser;
let Message = new Map();


function connectToChat(username){

    console.log("connecting to chat...");

    let socket = new SockJS(url+'/chat');
    stompClient = Stomp.over(socket);


    stompClient.connect({},function (frame){

        console.log(`frame : {}`,frame);


        stompClient.subscribe("/topic/messages/" + username,function (message){


            let data = JSON.parse(message.body);
            alert('result : '+(data.fromLogin == selectedUser));

            if (selectedUser != data.fromLogin){
                newMessage.set(data.fromLogin, data.message);
                $('#userNameAppender_'+ data.formLogin).append()

            }

        })

    })

}