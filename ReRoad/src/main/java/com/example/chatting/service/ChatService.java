package com.example.chatting.service;


import com.example.chatting.dao.ChatDao;
import com.example.chatting.domain.*;
import com.example.member.vo.UserAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;


@Service
public class ChatService {

    private static final Logger logger = LoggerFactory.getLogger(ChatService.class);
    private Map<ChatRequest, DeferredResult<ChatResponse>> waitingUsers;
    // {key : websocket session id, value : chat room id}
    private Map<String, String> connectedUsers;
    private ReentrantReadWriteLock lock;
    private List<ChatUserVO> users = new ArrayList<ChatUserVO>() ;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private ChatDao chatDao;


    @PostConstruct


    private void setUp() {
        this.waitingUsers = new LinkedHashMap<>();
        this.lock = new ReentrantReadWriteLock();
        this.connectedUsers = new ConcurrentHashMap<>();
    }
    public String findNick(String userId){
        return this.chatDao.findNick(userId);
    }
    /*public void joinUser (String sessionId, String name){
        ChatUserVO chatuser = new ChatUserVO();
         chatuser.setUsername(name);
        chatuser.setSessionId(sessionId);
        users.add(chatuser);
    }
*/
    @Async("asyncThreadPool")

    public void joinChatRoom(ChatRequest request, DeferredResult<ChatResponse> deferredResult) {
        logger.info("## Join chat room request. {}[{}]", Thread.currentThread().getName(), Thread.currentThread().getId());
        if (request == null || deferredResult == null) {
            return;
        }

        try {
            lock.writeLock().lock();
            waitingUsers.put(request, deferredResult);
        } finally {
            lock.writeLock().unlock();
            establishChatRoom();
        }
    }

    public void cancelChatRoom(ChatRequest chatRequest) {
        try {
            lock.writeLock().lock();
            setJoinResult(waitingUsers.remove(chatRequest), new ChatResponse(ChatResponse.ResponseResult.CANCEL, null, chatRequest.getSessionId(),chatRequest.getUsername()));
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void timeout(ChatRequest chatRequest) {
        try {
            lock.writeLock().lock();
            setJoinResult(waitingUsers.remove(chatRequest), new ChatResponse(ChatResponse.ResponseResult.TIMEOUT, null, chatRequest.getSessionId(), chatRequest.getUsername()));
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void establishChatRoom() {
        try {
            logger.debug("Current waiting users : " + waitingUsers.size());
            lock.readLock().lock();

            Iterator<ChatRequest> itr = waitingUsers.keySet().iterator();

            /*for(int i=0; i<50; i++){
                ChatRequest user = itr.next();*/
            ChatRequest user = itr.next();
            //  String uuid = UUID.randomUUID().toString();

            DeferredResult<ChatResponse> userResult = waitingUsers.remove(user);

            userResult.setResult(new ChatResponse(ChatResponse.ResponseResult.SUCCESS, "reroad", user.getSessionId(), user.getUsername()));
           /* ChatUserVO chatuser = new ChatUserVO();
             chatuser.setUsername(user.getUsername());
            //chatuser.setSessionId(user.getSessionId());
            users.add(chatuser);*/
            //   }
           /* ChatRequest user1 = itr.next();
            ChatRequest user2 = itr.next();

            String uuid = UUID.randomUUID().toString();

            DeferredResult<ChatResponse> user1Result = waitingUsers.remove(user1);
            DeferredResult<ChatResponse> user2Result = waitingUsers.remove(user2);

            user1Result.setResult(new ChatResponse(ChatResponse.ResponseResult.SUCCESS, uuid, user1.getSessionId()));
            user2Result.setResult(new ChatResponse(ChatResponse.ResponseResult.SUCCESS, uuid, user2.getSessionId()));*/
        } catch (Exception e) {
            logger.warn("Exception occur while checking waiting users", e);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void sendMessage(String chatRoomId, ChatMessage chatMessage) {
        String destination = getDestination(chatRoomId);
        messagingTemplate.convertAndSend(destination, chatMessage);
    }

    public void connectUser(String username, String websocketSessionId) {
        connectedUsers.put(username, websocketSessionId);

        ChatMessage chatMessage = new ChatMessage();

        chatMessage.setMessageType(MessageType.CONNECTED);
        chatMessage.setUsername(username);

        sendMessage("reroad", chatMessage);
    }

    public void disconnectUser(String websocketSessionId, String username) {

        ChatMessage chatMessage = new ChatMessage();

        chatMessage.setMessageType(MessageType.DISCONNECTED);
        chatMessage.setUsername(username);

        sendMessage("reroad", chatMessage);
    }

    private String getDestination(String chatRoomId) {
        return "/topic/chat/" + chatRoomId;
    }

    private void setJoinResult(DeferredResult<ChatResponse> result, ChatResponse response) {
        if (result != null) {
            result.setResult(response);
        }
    }
}