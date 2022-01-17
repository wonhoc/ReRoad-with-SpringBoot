package com.example.chatting.domain;

import java.util.Objects;

public class ChatRequest {

    private String sessionId;
    private String username;

    public ChatRequest() {
    }

    public ChatRequest(String sessionId, String username) {
        this.sessionId = sessionId; this.username = username;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUsername() { return username;}

    public void setUsername(String username) { this.username = username; }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChatRequest)) {
            return false;
        }

        ChatRequest that = (ChatRequest) o;
        return Objects.equals(this.sessionId, that.sessionId);
    }

    @Override
    public int hashCode() {
        return sessionId.hashCode();
    }

    @Override
    public String toString() {
        return "ChatRequest{" + "sessionId='" + sessionId + '\'' + "username='" + username + '\'' + '}';
    }
}