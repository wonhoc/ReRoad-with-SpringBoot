package com.example.domesitc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

@Controller
public class DomesticController {


    @GetMapping("/domestic")
    public String domectic() throws IOException {


        return "views/domestic/domestic";
    }


}
