package com.example.chatting.domain;


public class ChatResponse {

    private ResponseResult responseResult;
    private String chatRoomId;
    private String sessionId;
    private String sendername;
    public ChatResponse() {
    }

    public ChatResponse(ResponseResult responseResult, String chatRoomId, String sessionId, String sendername) {
        this.responseResult = responseResult;
        this.chatRoomId = chatRoomId;
        this.sessionId = sessionId;
        this.sendername = sendername;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(String chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public ResponseResult getResponseResult() {
        return responseResult;
    }

    public void setResponseResult(ResponseResult responseResult) {
        this.responseResult = responseResult;
    }

    public String getSendername() {return sendername;}

    public void setSendername(String sendername) {this.sendername = sendername;}

    @Override
    public String toString() {
        return "ChatResponse{" + "responseResult=" + responseResult + ", chatRoomId='" + chatRoomId + '\'' + ", sessionId='" + sessionId + '\'' +
                ", sendername='" + sendername + '\'' +'}';
    }

    public enum ResponseResult {
        SUCCESS, CANCEL, TIMEOUT;
    }
}
