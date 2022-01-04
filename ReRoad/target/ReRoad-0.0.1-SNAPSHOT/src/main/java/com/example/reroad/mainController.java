package com.example.reroad;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainController {
    @GetMapping("/")
    public String main() {
        return "main.html";
    }

    @GetMapping("/test")
    public String error() {
        throw new RuntimeException("hello");
    }

}
