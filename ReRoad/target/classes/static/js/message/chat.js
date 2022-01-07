const url = 'http://localhost:8080';
let stompClient;
let username;
let newMessage = new Map();


$(document).ready(function () {

    let username = $('#username').val();

    console.log(username);

    if (username != null) {

        console.log("connecting to chat...");

        let socket = new SockJS(url + '/chat');
        stompClient = Stomp.over(socket);



        stompClient.connect({}, function (frame) {

            stompClient.subscribe("/topic/messages/" + username, function (message) {
                let data = JSON.parse(message.body);
                console.log("hi")
                alert('result : ' + (data.fromLogin == username));

                $('#alarm').append("");

                let str = '<strong id=\'newMessage\' style=\'color: red;\'>new</strong>'

                $('#alarm').append(str);
                render(data.message, data.fromLogin);
            });

        });

        function render(message, username) {

            // responses
            var templateResponse = Handlebars.compile($("#alarm").html());
            var contextResponse = {
                response: message,
                username: username
            };

        }


    }
});


