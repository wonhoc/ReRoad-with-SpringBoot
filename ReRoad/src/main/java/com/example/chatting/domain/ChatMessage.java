package com.example.chatting.domain;

public class ChatMessage {

    private String senderSessionId;
    private String username;
    private String message;
    private MessageType messageType;

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getSenderSessionId() {
        return senderSessionId;
    }

    public void setSenderSessionId(String senderSessionId) {
        this.senderSessionId = senderSessionId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) {this.username = username;}
    @Override
    public String toString() {
        return "ChatMessage{" + "senderSessionId='" + senderSessionId + '\'' + ", message='" + message + '\'' + ", messageType=" + messageType
                + '\'' + ", username=" + username+ '}';
    }
}