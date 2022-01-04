package com.example.domesitc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DomesticController {


    @GetMapping("/domestic")
    public String domectic(){

        return "views/domestic/domestic";
    }
}
