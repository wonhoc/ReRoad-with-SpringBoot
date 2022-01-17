package com.example.chatting.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatUserVO {
    private String username;
    private String sessionId;
}
