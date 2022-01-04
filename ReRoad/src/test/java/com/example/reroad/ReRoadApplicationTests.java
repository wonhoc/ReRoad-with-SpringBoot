package com.example.reroad;

import com.example.member.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@ComponentScan(basePackages = "com.example")
@WebAppConfiguration
@SpringBootTest
class ReRoadApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;
    @Test
    void contextLoads() {

//        try {
//            Map<String, String> map = new HashMap<String, String>();
//            map.put("userId", "member7894");
//            map.put("userPwd", encoder.encode("asdf1234"));
//            map.put("userNick", "나는멤버7894");
//            map.put("role", "ROLE_MEMBER");
//            this.userService.registUser(map, "mgdee");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

}
