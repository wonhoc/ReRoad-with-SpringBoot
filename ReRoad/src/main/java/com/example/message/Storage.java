package com.example.message;

import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Slf4j
public class Storage {

    private static Storage instance;
    private Set<String> users;

    public static synchronized Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }




    public Set<String> getUsers() {
        return users;
    }

    public void setUser(String userName) throws Exception {
        if (users.contains(userName)) {
            throw new Exception("User aready exists with userName: " + userName);
        }
        users.add(userName);
    }
}
