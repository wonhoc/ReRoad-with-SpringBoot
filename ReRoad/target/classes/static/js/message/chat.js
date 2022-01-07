const url = 'http://ec2-54-180-31-9.ap-northeast-2.compute.amazonaws.com';
let stompClient;
let username;
let newMessage = new Map();

toastr.options = {
    "closeButton": false,
    "debug": false,
    "newestOnTop": false,
    "progressBar": true,
    "positionClass": "toast-top-right",
    "preventDuplicates": false,
    "showDuration": "2000",
    "hideDuration": "1000",
    "timeOut": "4500",
    "extendedTimeOut": "4500",
    "showEasing": "swing",
    "hideEasing": "linear",
    "showMethod": "fadeIn",
    "hideMethod": "fadeOut"
}


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

                let boardNo = data.boardNo;

                toastr.options.onclick = function() { location.href='/detailBoard/'+boardNo }
                toastr.info(data.fromLogin+"님이 댓글을 남기셨습니다.");

                render(data.message, data.fromLogin,data.boardNo);
            });

        });

        function render(message, username) {
            var contextResponse = {
                response: message,
                username: username,
                boardNo : boardNo
            };

        }


    }
});


