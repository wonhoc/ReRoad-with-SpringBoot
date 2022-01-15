package com.example.chatting.web;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;


@RestController
@CrossOrigin
public class UsersController {

    @GetMapping("/registration/{username}")
    public ResponseEntity<Void> register(@PathVariable String username) {
        System.out.println("handling register user request: " + username);
        try {
            ChatUserStorage.getInstance().setUser(username);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }



    @GetMapping("/fetchAllUsers")
    public Set<String> fetchAll() {

        System.out.println(ChatUserStorage.getInstance().getUsers().toString());
        return ChatUserStorage.getInstance().getUsers();
    }
}
