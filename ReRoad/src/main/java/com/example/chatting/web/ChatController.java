package com.example.chatting.web;


import com.example.chatting.domain.ChatMessage;
import com.example.chatting.domain.ChatRequest;
import com.example.chatting.domain.ChatResponse;
import com.example.chatting.domain.MessageType;
import com.example.chatting.service.ChatService;
import com.example.chatting.util.ServletUtil;
import com.example.member.vo.UserAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;


@RestController
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    private ChatService chatService;

    // tag :: async
    @GetMapping("/member/join")
    @ResponseBody
    public DeferredResult<ChatResponse> joinRequest(@AuthenticationPrincipal UserAccount nowuser) {
        String sessionId = ServletUtil.getSession().getId();
        String username = nowuser.getUser().getUserNick();
        logger.info(">> Join request. session id : {}", sessionId, username);

        final ChatRequest user = new ChatRequest(sessionId, username);
        final DeferredResult<ChatResponse> deferredResult = new DeferredResult<>(null);
        chatService.joinChatRoom(user, deferredResult);

        deferredResult.onCompletion(() -> chatService.cancelChatRoom(user));
        deferredResult.onError((throwable) -> chatService.cancelChatRoom(user));
        deferredResult.onTimeout(() -> chatService.timeout(user));

        return deferredResult;
    }

    @GetMapping("/cancel")
    @ResponseBody
    public ResponseEntity<Void> cancelRequest(@AuthenticationPrincipal UserAccount nowuser) {
        String sessionId = ServletUtil.getSession().getId();
        String username = nowuser.getUser().getUserNick();
        logger.info(">> Cancel request. session id : {}", sessionId,username);

        final ChatRequest user = new ChatRequest(sessionId, username);
        chatService.cancelChatRoom(user);

        return ResponseEntity.ok().build();
    }

    // -- tag :: async

    // tag :: websocket stomp
    @MessageMapping("/chat.message/{chatRoomId}")
    public void sendMessage(@DestinationVariable("chatRoomId") String chatRoomId, @Payload ChatMessage chatMessage) {
        logger.info("Request message. roomd id : {} | chat message : {} | principal : {}", chatRoomId, chatMessage);
        if (!StringUtils.hasText(chatRoomId) || chatMessage == null) {
            return;
        }

        if (chatMessage.getMessageType() == MessageType.CHAT) {
            chatService.sendMessage(chatRoomId, chatMessage);
        }
    }
    // -- tag :: websocket stomp
}
