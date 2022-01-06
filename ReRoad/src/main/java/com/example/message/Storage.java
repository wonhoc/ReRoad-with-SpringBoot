package com.example.message;

import java.util.Set;

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

}
