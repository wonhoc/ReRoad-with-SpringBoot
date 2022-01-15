package com.example.chatting.web;

import java.util.HashSet;
import java.util.Set;

public class ChatUserStorage {
    private static ChatUserStorage instance;
    private Set<String> users;

    private ChatUserStorage() {
        users = new HashSet<>();
    }

    public static synchronized ChatUserStorage getInstance() {
        if (instance == null) {
            instance = new ChatUserStorage();
        }
        return instance;
    }

    public Set<String> getUsers() {
        return users;
    }

    public void setUser(String userName) throws Exception {
        if (!users.contains(userName)) {
            users.add(userName);
        }

    }
}
