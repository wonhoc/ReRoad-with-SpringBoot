package com.example.chatting.web;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChatUserStorage {
    private static ChatUserStorage instance;
    private List<String> users;

    private ChatUserStorage() {
        users = new ArrayList<>();
    }

    public static synchronized ChatUserStorage getInstance() {
        if (instance == null) {
            instance = new ChatUserStorage();
        }
        return instance;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUser(String userName) throws Exception {
        if (!users.contains(userName)) {
            users.add(userName);
        }

    }

    public void deleteUser(int i){
        users.remove(i);
    }
}
